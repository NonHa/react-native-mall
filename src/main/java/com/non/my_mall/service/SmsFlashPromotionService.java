package com.non.my_mall.service;

import com.non.my_mall.dto.SmsFlashPromotionParam;
import com.non.my_mall.mbg.model.SmsFlashPromotion;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SmsFlashPromotionService {
    List<SmsFlashPromotion> getFlashList(SmsFlashPromotionParam param);
    int updateFlashById(SmsFlashPromotion param);
    int addFlash(SmsFlashPromotion param);
}
