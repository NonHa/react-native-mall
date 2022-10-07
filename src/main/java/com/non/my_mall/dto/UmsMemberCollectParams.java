package com.non.my_mall.dto;

import cn.hutool.log.Log;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UmsMemberCollectParams extends PageParams{
    @NotNull
    @ApiModelProperty(value = "用户Id", required = true)
    private Long memberId;

    @NotNull
    @ApiModelProperty(value = "收藏类型", required = true)
    private Long collectType;

}
