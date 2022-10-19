package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.OmsCartItem;

import java.util.List;

public interface OmsCarItemDao {
    int add(OmsCartItem params);
    int update(OmsCartItem params);
    List<OmsCartItem> getList(Long memberId);

}
