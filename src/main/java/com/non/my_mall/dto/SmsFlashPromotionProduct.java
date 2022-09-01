package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.PmsProduct;
import com.non.my_mall.mbg.model.SmsFlashPromotionProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {
    @ApiModelProperty("关联的商品信息")
    private PmsProduct product;


}
