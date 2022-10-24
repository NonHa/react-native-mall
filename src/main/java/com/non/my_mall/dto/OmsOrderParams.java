package com.non.my_mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OmsOrderParams extends PageParams{
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "收货人姓名/电话号码")
    private String receiverKeyWord;

    @ApiModelProperty(value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;

    @ApiModelProperty(value = "确认收货状态：0->未确认；1->已确认")
    private Integer confirmStatus;
    @ApiModelProperty(value = "订单类型：0->正常订单；1->秒杀订单")
    private Integer orderType;

    @ApiModelProperty(value = "订单来源：0->PC订单；1->app订单")
    private Integer sourceType;

    @ApiModelProperty(value = "提交时间")
    private Date createTime;

    @ApiModelProperty(value = "客户id")
    private Long memberId;

}
