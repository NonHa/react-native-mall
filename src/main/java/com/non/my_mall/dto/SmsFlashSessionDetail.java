package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.SmsFlashPromotionSession;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SmsFlashSessionDetail extends SmsFlashPromotionSession {
    @ApiModelProperty(value = "商品数量")
    private Long productCount;
}
