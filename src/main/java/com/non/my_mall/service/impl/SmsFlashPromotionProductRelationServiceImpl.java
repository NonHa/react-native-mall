package com.non.my_mall.service.impl;

import com.non.my_mall.dao.SmsFlashPromotionProductRelationDao;
import com.non.my_mall.dto.SmsFlashPromotionProduct;
import com.non.my_mall.mbg.model.SmsFlashPromotionProductRelation;
import com.non.my_mall.service.SmsFlashPromotionProductRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    @Resource
    private SmsFlashPromotionProductRelationDao flashPromotionProductRelationDao;
    @Override
    public Long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        return flashPromotionProductRelationDao.getCount(flashPromotionId,flashPromotionSessionId);
    }

    @Override
    public List<SmsFlashPromotionProduct> getList(Long promotionId, Long sessionId) {
        return flashPromotionProductRelationDao.getList(promotionId,sessionId);
    }
    @Override
    public int create(List<SmsFlashPromotionProductRelation> param) {
        for (SmsFlashPromotionProductRelation item:  param) {
            flashPromotionProductRelationDao.create(item);
        }
        return param.size();
    }

    @Override
    public int delete(Long id) {
        return flashPromotionProductRelationDao.delete(id);
    }

    @Override
    public int update(SmsFlashPromotionProduct param) {
        return flashPromotionProductRelationDao.update(param);
    }

    public static interface FrontUserDetailService {

    }
}
