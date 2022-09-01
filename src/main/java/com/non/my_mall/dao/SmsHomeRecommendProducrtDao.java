package com.non.my_mall.dao;

import com.non.my_mall.dto.SmsHomeNewProductParam;
import com.non.my_mall.dto.SmsHomeRecommendProductParams;
import com.non.my_mall.mbg.model.SmsHomeRecommendProduct;

import java.util.List;

public interface SmsHomeRecommendProducrtDao {
    List<SmsHomeRecommendProduct> getList(SmsHomeRecommendProductParams param);
    int deleteById(Long id);
    int update(SmsHomeRecommendProduct param);
    int insert(List<SmsHomeRecommendProduct> param);
}
