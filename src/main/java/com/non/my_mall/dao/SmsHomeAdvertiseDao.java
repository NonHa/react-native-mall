package com.non.my_mall.dao;

import com.non.my_mall.dto.SmsHomeAdvertiseParams;
import com.non.my_mall.mbg.model.SmsHomeAdvertise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsHomeAdvertiseDao {
    List<SmsHomeAdvertise> getList(SmsHomeAdvertiseParams params);
    int deleteById(@Param("id") Long id);
    int updateById(SmsHomeAdvertise param);
    int addItem(SmsHomeAdvertise param);
}
