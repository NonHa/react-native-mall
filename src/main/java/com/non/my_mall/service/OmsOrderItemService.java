package com.non.my_mall.service;

import com.non.my_mall.mbg.model.OmsCartItem;

import java.util.List;

public interface OmsOrderItemService {
    int insertList(List<OmsCartItem> cartItems);
}
