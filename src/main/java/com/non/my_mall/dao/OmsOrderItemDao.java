package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.OmsCartItem;
import com.non.my_mall.mbg.model.OmsOrderItem;

import java.util.List;

public interface OmsOrderItemDao {
    int insertList(List<OmsOrderItem> list);
    int update(OmsOrderItem param);
    Integer confirmOrder(Long id);

}
