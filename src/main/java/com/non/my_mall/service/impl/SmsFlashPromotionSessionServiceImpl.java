package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.non.my_mall.dao.SmsFlashPromotionProductRelationDao;
import com.non.my_mall.dao.SmsFlashPromotionSessionDao;
import com.non.my_mall.dto.SmsFlashSessionDetail;
import com.non.my_mall.mbg.model.SmsFlashPromotionSession;
import com.non.my_mall.service.SmsFlashPromotionSessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {
    @Resource
    private SmsFlashPromotionSessionDao flashPromotionSessionDao;

    @Resource
    private SmsFlashPromotionProductRelationDao promotionProductRelationDao;
    @Override
    public List<SmsFlashPromotionSession> getList() {
        return flashPromotionSessionDao.getList();
    }

    @Override
    public int updateSession(SmsFlashPromotionSession param) {
        return flashPromotionSessionDao.updateSession(param);
    }

    @Override
    public int add(SmsFlashPromotionSession param) {
        param.setCreateTime(new Date());
        return flashPromotionSessionDao.add(param);
    }

    @Override
    public List<SmsFlashSessionDetail> selectList(Long flashPromotionId) {
        ArrayList<SmsFlashSessionDetail> objects = new ArrayList<>();
        List<SmsFlashPromotionSession> list = flashPromotionSessionDao.getList();
        for (SmsFlashPromotionSession item:  list) {
            SmsFlashSessionDetail smsFlashSessionDetail = new SmsFlashSessionDetail();
            BeanUtil.copyProperties(item, smsFlashSessionDetail);
            Long count = promotionProductRelationDao.getCount(flashPromotionId, item.getId());
            smsFlashSessionDetail.setProductCount(count);
            objects.add(smsFlashSessionDetail);
        }
        return objects;
    }
}
