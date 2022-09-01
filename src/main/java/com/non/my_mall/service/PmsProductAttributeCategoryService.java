package com.non.my_mall.service;

import com.non.my_mall.dto.PmsProductAttributeCategoryItem;
import com.non.my_mall.mbg.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * 商品属性分类管理Service
 * */
public interface PmsProductAttributeCategoryService {
    List<PmsProductAttributeCategory> getList(PmsProductAttributeCategory params,Integer pageNum, Integer pageSize);
    int deleteById(Long id);
    int creatProductAttrCategory(PmsProductAttributeCategory name);
    List<PmsProductAttributeCategoryItem> getAttrWith();
}
