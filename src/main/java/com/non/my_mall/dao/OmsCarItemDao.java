package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.OmsCartItem;

public interface OmsCarItemDao {
    int add(OmsCartItem params);
    int update(OmsCartItem params);
}
