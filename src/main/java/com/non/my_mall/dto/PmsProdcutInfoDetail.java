package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.PmsBrand;
import com.non.my_mall.mbg.model.PmsComment;
import com.non.my_mall.mbg.model.PmsProduct;
import lombok.Data;

import java.util.List;

@Data
public class PmsProdcutInfoDetail extends PmsProduct {
    private PmsComment comment;
    private Integer commentCount;
    private List<PmsProduct> recommendList;
    private PmsBrand brandInfo;
}
