package com.non.my_mall.service;

import com.non.my_mall.dto.OmsOrderReturnApplyParam;
import com.non.my_mall.dto.OmsUpdateParam;
import com.non.my_mall.mbg.model.OmsOrderReturnApply;

import java.util.List;

public interface OmsOrderReturnApplyService {
    List<OmsOrderReturnApply> getList(OmsOrderReturnApplyParam param);

    int update(OmsUpdateParam param);
}
