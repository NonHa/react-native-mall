package com.non.my_mall.service;

import com.non.my_mall.dto.OmsOrderReturnReasonParam;
import com.non.my_mall.mbg.model.OmsOrderReturnReason;

import java.util.List;

public interface OmsOrderReturnReasonService {
    List<OmsOrderReturnReason> getList(OmsOrderReturnReasonParam param);
    int update(OmsOrderReturnReason param);
    int addReason(OmsOrderReturnReason param);

}
