package com.non.my_mall.service;

import com.non.my_mall.dto.PmsProductCategoryParams;
import com.non.my_mall.dto.PmsProductCategoryWithChildrenItem;
import com.non.my_mall.dto.PmsProductQueryParam;
import com.non.my_mall.mbg.model.PmsProduct;
import com.non.my_mall.mbg.model.PmsProductCategory;

import java.util.List;

public interface PmsProductCategoryService {
    /**
     * 分页查询商品
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
    List<PmsProductCategory> getList(PmsProductCategory params, Integer page, Integer pageSize);
    int delete(Long id);
    int createProductCate(PmsProductCategoryParams params);
    int updateProductCate(PmsProductCategoryParams params);
}
