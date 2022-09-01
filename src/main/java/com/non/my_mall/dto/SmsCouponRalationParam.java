package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.SmsCoupon;
import com.non.my_mall.mbg.model.SmsCouponProductCategoryRelation;
import com.non.my_mall.mbg.model.SmsCouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SmsCouponRalationParam extends SmsCoupon {
    @ApiModelProperty("优惠券绑定的商品")
    private List<SmsCouponProductRelation> productRelationList;

    @ApiModelProperty("优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelation> productCategoryRelationList;
}
