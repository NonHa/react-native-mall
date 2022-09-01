package com.non.my_mall.service;

import com.non.my_mall.mbg.model.PmsProductAttribute;

import java.util.List;

public interface PmsProductAttributeService {
    List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageNum, Integer pageSize);
    int deleteProductAttributeById(Long id);
    int creatAttribute(PmsProductAttribute params);
}
