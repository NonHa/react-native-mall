package com.non.my_mall.service;

import com.non.my_mall.dto.SmsHomeAdvertiseParams;
import com.non.my_mall.mbg.model.SmsHomeAdvertise;

import java.util.List;

public interface SmsHomeAdvertiseService {
    List<SmsHomeAdvertise> getList(SmsHomeAdvertiseParams params);
    int deleteById(Long id);
    int updateById(SmsHomeAdvertise param);
    int addItem(SmsHomeAdvertise param);
}
