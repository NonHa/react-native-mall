package com.non.my_mall.dao;

import com.non.my_mall.dto.SmsHomeBrandParam;
import com.non.my_mall.dto.SmsHomeNewProductParam;
import com.non.my_mall.mbg.model.SmsHomeBrand;
import com.non.my_mall.mbg.model.SmsHomeNewProduct;

import java.util.List;

public interface SmsHomeNewProducrDao {
    List<SmsHomeNewProduct> getList(SmsHomeNewProductParam param);
    int deleteById(Long id);
    int update(SmsHomeNewProduct param);
    int insert(List<SmsHomeNewProduct> param);
}
