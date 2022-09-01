package com.non.my_mall.dao;

import com.non.my_mall.dto.SmsFlashSessionDetail;
import com.non.my_mall.mbg.model.SmsFlashPromotionSession;

import java.util.List;

public interface SmsFlashPromotionSessionDao {
    List<SmsFlashPromotionSession> getList();
    int updateSession(SmsFlashPromotionSession param);
    int add(SmsFlashPromotionSession param);
    List<SmsFlashSessionDetail> selectList(Long flashPromotionId);

}
