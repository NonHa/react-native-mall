package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.non.my_mall.mbg.mapper.PmsProductAttributeCategoryMapper;
import com.non.my_mall.mbg.mapper.PmsProductAttributeMapper;
import com.non.my_mall.mbg.model.PmsProductAttribute;
import com.non.my_mall.mbg.model.PmsProductAttributeCategory;
import com.non.my_mall.mbg.model.PmsProductAttributeExample;
import com.non.my_mall.service.PmsProductAttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {
    @Resource
    private PmsProductAttributeMapper productAttributeMapper;

    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PmsProductAttributeExample productAttributeExample = new PmsProductAttributeExample();
        productAttributeExample.createCriteria().andProductAttributeCategoryIdEqualTo(cid).andTypeEqualTo(type);
        return productAttributeMapper.selectByExample(productAttributeExample);
    }

    @Override
    public int deleteProductAttributeById(Long id) {
        PmsProductAttribute pmsProductAttribute = productAttributeMapper.selectByPrimaryKey(id);
        Integer type = pmsProductAttribute.getType();

        // 更新商品类型中的各个类型 数量的值
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        if (type == 0) {
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount() - 1);
        } else if (type == 1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount() - 1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return productAttributeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int creatAttribute(PmsProductAttribute params) {
        PmsProductAttribute productAttributeExample = new PmsProductAttribute();
        BeanUtil.copyProperties(params,productAttributeExample);
        // 更新商品类型中的各个类型 数量的值
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(productAttributeExample.getProductAttributeCategoryId());
        if (productAttributeExample.getType() == 0) {
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount() + 1);
        } else if (productAttributeExample.getType() == 1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount() + 1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return productAttributeMapper.insertSelective(productAttributeExample);
    }
}
