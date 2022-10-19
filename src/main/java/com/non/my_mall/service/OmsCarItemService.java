package com.non.my_mall.service;

import com.non.my_mall.domain.CartPromotionItem;
import com.non.my_mall.mbg.model.OmsCartItem;

import java.util.List;

public interface OmsCarItemService {
    int add(OmsCartItem params);
    List<OmsCartItem> getList();
    List<CartPromotionItem> listPromotion(Long memberId, List<Long> carIds);
}
