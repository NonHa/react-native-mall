package com.non.my_mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UmsMenuParam extends PageParams{
    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "是否展示")
    private Integer hidden;
}
