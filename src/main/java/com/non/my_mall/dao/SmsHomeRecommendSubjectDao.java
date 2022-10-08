package com.non.my_mall.dao;

import com.non.my_mall.dto.SmsHomeNewProductParam;
import com.non.my_mall.dto.SmsHomeRecommendSubjectDetail;
import com.non.my_mall.dto.SmsHomeRecommendSubjectParam;
import com.non.my_mall.mbg.model.SmsHomeNewProduct;
import com.non.my_mall.mbg.model.SmsHomeRecommendSubject;

import java.util.List;

public interface SmsHomeRecommendSubjectDao {
    List<SmsHomeRecommendSubject> getList(SmsHomeRecommendSubjectParam param);
    int deleteById(Long id);
    int update(SmsHomeRecommendSubject param);
    int insert(List<SmsHomeRecommendSubject> param);
    List<SmsHomeRecommendSubject> getInfoList(SmsHomeRecommendSubjectParam param);
    SmsHomeRecommendSubjectDetail getDetailById(Long id);
}
