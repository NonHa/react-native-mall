package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.SmsFlashPromotionDao;
import com.non.my_mall.dto.SmsFlashPromotionParam;
import com.non.my_mall.mbg.model.SmsFlashPromotion;
import com.non.my_mall.service.SmsFlashPromotionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {
    @Resource
    private SmsFlashPromotionDao flashPromotionDao;
    @Override
    public List<SmsFlashPromotion> getFlashList(SmsFlashPromotionParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        return flashPromotionDao.getFlashList(param);
    }

    @Override
    public int updateFlashById(SmsFlashPromotion param) {
        return flashPromotionDao.updateFlashById(param);
    }

    @Override
    public int addFlash(SmsFlashPromotion param) {
        param.setCreateTime(new Date());
        return flashPromotionDao.addFlash(param);
    }
}
