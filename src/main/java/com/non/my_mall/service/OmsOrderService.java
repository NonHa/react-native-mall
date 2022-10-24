package com.non.my_mall.service;

import com.non.my_mall.dto.*;
import com.non.my_mall.mbg.model.OmsOrder;

import java.util.List;
import java.util.Map;

public interface OmsOrderService {
    List<OmsOrder> getList(OmsOrderParams param);
    int delete(Long id);
    Map<String, Object> generate(OmsGenerateOrderParam param);
    ConfirmOrderResult generateConfirmOrder(List<Long> carIds);
    List<OmsOrderDetail> getMember(OmsOrderParams param);
    Integer paySuccess(OrderPayParam param);
    Integer confirmOrder(List<Long> ids);
}
