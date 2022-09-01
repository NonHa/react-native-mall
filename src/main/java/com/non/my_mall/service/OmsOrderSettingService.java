package com.non.my_mall.service;

import com.non.my_mall.mbg.model.OmsOrderSetting;

public interface OmsOrderSettingService {
   OmsOrderSetting getSetting();
   int updateOrderSetting(OmsOrderSetting param);
}
