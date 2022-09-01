package com.non.my_mall.service.impl;

import com.non.my_mall.dao.OmsOrderSettingDao;
import com.non.my_mall.mbg.model.OmsOrderSetting;
import com.non.my_mall.service.OmsOrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    @Resource
    private OmsOrderSettingDao orderSettingDao;
    @Override
    public OmsOrderSetting getSetting() {
        return orderSettingDao.getList();
    }

    @Override
    public int updateOrderSetting(OmsOrderSetting param) {
        return orderSettingDao.updateOrderSetting(param);
    }
}
