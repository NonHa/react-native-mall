package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.OmsOrderSetting;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderSettingDao {
    OmsOrderSetting getList();
    int updateOrderSetting( OmsOrderSetting param);

}
