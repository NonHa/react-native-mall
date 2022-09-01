package com.non.my_mall.dao;

import com.non.my_mall.dto.SmsHomeBrandParam;
import com.non.my_mall.mbg.model.SmsHomeBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsHomeBrandDao {
    List<SmsHomeBrand> getList(SmsHomeBrandParam param);
    int deleteById(Long id);
    int update(SmsHomeBrand param);
    int insert(@Param("list") List<SmsHomeBrand> param);
}
