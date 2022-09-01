package com.non.my_mall.service;

import com.non.my_mall.dto.SmsFlashPromotionProduct;
import com.non.my_mall.mbg.model.SmsFlashPromotionProductRelation;

import java.util.List;

public interface SmsFlashPromotionProductRelationService {
    /**
     * 根据活动和场次id获取商品关系数量
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    Long getCount(Long flashPromotionId,Long flashPromotionSessionId);

    List<SmsFlashPromotionProduct> getList(Long promotionId, Long sessionId);
    int create(List<SmsFlashPromotionProductRelation> param);
    int delete(Long id);
    int update(SmsFlashPromotionProduct param);

}
