package com.non.my_mall.service;

import com.non.my_mall.dto.SmsHomeBrandParam;
import com.non.my_mall.mbg.model.SmsHomeBrand;

import java.util.List;

public interface SmsHomeBrandService {
    List<SmsHomeBrand> getList(SmsHomeBrandParam param);
    int deleteById(Long id);
    int update(SmsHomeBrand param);
    int insert(List<SmsHomeBrand> param);
}
