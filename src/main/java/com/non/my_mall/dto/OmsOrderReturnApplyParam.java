package com.non.my_mall.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class OmsOrderReturnApplyParam extends PageParams{
    private Long id;

    @ApiModelProperty(value = "订单id")
    private Long orderId;
    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;

    @ApiModelProperty(value = "申请时间")
    private Date createTime;

    @ApiModelProperty(value = "处理人员")
    private String handleMan;

    @ApiModelProperty(value = "处理时间")
    private Date handleTime;

}
