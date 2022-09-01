package com.non.my_mall.dao;

import com.non.my_mall.dto.SmsFlashPromotionParam;
import com.non.my_mall.mbg.model.SmsFlashPromotion;

import java.util.List;

public interface SmsFlashPromotionDao {
    List<SmsFlashPromotion> getFlashList(SmsFlashPromotionParam param);
    int updateFlashById(SmsFlashPromotion param);
    int addFlash(SmsFlashPromotion param);

}
