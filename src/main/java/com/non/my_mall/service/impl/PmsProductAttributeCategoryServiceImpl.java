package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.PmsProductAttributeCategoryDao;
import com.non.my_mall.dto.PmsProductAttributeCategoryItem;
import com.non.my_mall.mbg.mapper.PmsProductAttributeCategoryMapper;
import com.non.my_mall.mbg.model.PmsProductAttributeCategory;
import com.non.my_mall.mbg.model.PmsProductAttributeCategoryExample;
import com.non.my_mall.service.PmsProductAttributeCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Resource
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    @Override
    public List<PmsProductAttributeCategory> getList(PmsProductAttributeCategory params,Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        PmsProductAttributeCategoryExample productAttributeCategoryExample = new PmsProductAttributeCategoryExample();
        PmsProductAttributeCategoryExample.Criteria criteria = productAttributeCategoryExample.createCriteria();

        if (params.getName() != null) {
            criteria.andNameLike("%" + params.getName() +"%");
        }
        return productAttributeCategoryMapper.selectByExample(productAttributeCategoryExample);
    }

    @Override
    public int deleteById(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int creatProductAttrCategory(PmsProductAttributeCategory name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        BeanUtil.copyProperties(name,productAttributeCategory);

        return productAttributeCategoryMapper.insertSelective(productAttributeCategory);
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getAttrWith() {
        return productAttributeCategoryDao.getListWith();
    }
}
