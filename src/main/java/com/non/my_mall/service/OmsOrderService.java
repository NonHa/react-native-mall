package com.non.my_mall.service;

import com.non.my_mall.dto.OmsOrderParams;
import com.non.my_mall.mbg.model.OmsOrder;

import java.util.List;

public interface OmsOrderService {
    List<OmsOrder> getList(OmsOrderParams param);
    int delete(Long id);

}
