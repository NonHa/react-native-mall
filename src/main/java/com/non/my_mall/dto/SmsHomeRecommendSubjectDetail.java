package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.CmsSubject;
import com.non.my_mall.mbg.model.PmsProduct;
import com.non.my_mall.mbg.model.SmsHomeRecommendSubject;
import lombok.Data;

import java.util.List;

@Data
public class SmsHomeRecommendSubjectDetail extends CmsSubject {
    private List<PmsProduct> productList;
}
