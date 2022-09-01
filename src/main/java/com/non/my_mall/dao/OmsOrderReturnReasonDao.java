package com.non.my_mall.dao;

import com.non.my_mall.dto.OmsOrderReturnReasonParam;
import com.non.my_mall.mbg.model.OmsOrderReturnReason;

import java.util.List;

public interface OmsOrderReturnReasonDao {
    List<OmsOrderReturnReason> getList(OmsOrderReturnReasonParam param);
    int update(OmsOrderReturnReason param);
    int addReason(OmsOrderReturnReason param);
}
