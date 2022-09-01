package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.PmsProductCategoryAndAttributeRelationDao;
import com.non.my_mall.dao.PmsProductCategoryDao;
import com.non.my_mall.dto.PmsProductCategoryParams;
import com.non.my_mall.dto.PmsProductCategoryWithChildrenItem;
import com.non.my_mall.mbg.mapper.PmsProductCategoryAttributeRelationMapper;
import com.non.my_mall.mbg.mapper.PmsProductCategoryMapper;
import com.non.my_mall.mbg.mapper.PmsProductMapper;
import com.non.my_mall.mbg.model.*;
import com.non.my_mall.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Resource
    private PmsProductCategoryMapper productCategoryMapper;
    @Resource
    private PmsProductCategoryDao productCategoryDao;
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsProductCategoryAndAttributeRelationDao productCategoryAndAttributeRelationDao;
    @Resource
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;
    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }

    @Override
    public List<PmsProductCategory> getList(PmsProductCategory params,Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        PmsProductCategoryExample productCategoryExample = new PmsProductCategoryExample();
        PmsProductCategoryExample.Criteria criteria = productCategoryExample.createCriteria();
        if (params.getParentId() != null) {
            criteria.andParentIdEqualTo(params.getParentId());
        }
        if (params.getName() != null) {
            criteria.andNameLike("%"+params.getName()+"%");
        }

        return productCategoryMapper.selectByExample(productCategoryExample);
    }

    @Override
    public int delete(Long id) {
        return productCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int createProductCate(PmsProductCategoryParams params) {
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        BeanUtil.copyProperties(params,pmsProductCategory);
        List<Long> attributeIdList = params.getProductAttributeIdList();
        int count = productCategoryMapper.insertSelective(pmsProductCategory);
        if (!CollectionUtils.isEmpty(attributeIdList)) {
            insertRelationList(pmsProductCategory.getId(), attributeIdList);
        }
        return count;
    }
    // 更新 relation
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttributeId: productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttributeId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAndAttributeRelationDao.insertList(relationList);
    }
    @Override
    public int updateProductCate(PmsProductCategoryParams params) {
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        BeanUtil.copyProperties(params,pmsProductCategory);
        //更新商品分类时要更新商品中的名称
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(params.getName());
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andProductCategoryIdEqualTo(params.getId());
        productMapper.updateByExampleSelective(product, productExample);

        PmsProductCategoryAttributeRelationExample productCategoryAttributeRelationExample = new PmsProductCategoryAttributeRelationExample();
        productCategoryAttributeRelationExample.createCriteria().andProductCategoryIdEqualTo(params.getId());
        productCategoryAttributeRelationMapper.deleteByExample(productCategoryAttributeRelationExample);
        if (!CollectionUtils.isEmpty(params.getProductAttributeIdList())) {
            insertRelationList(params.getId(), params.getProductAttributeIdList());
        }
        return productCategoryMapper.updateByPrimaryKeySelective(pmsProductCategory);
    }
}
