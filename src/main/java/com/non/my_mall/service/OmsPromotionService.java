package com.non.my_mall.service;

import com.non.my_mall.domain.CartPromotionItem;
import com.non.my_mall.mbg.model.OmsCartItem;

import java.util.List;

public interface OmsPromotionService {
    /**
     * 计算购物车中的促销活动信息
     * @param cartItemList 购物车
     */
    List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}
