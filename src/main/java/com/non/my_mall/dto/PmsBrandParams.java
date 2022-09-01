package com.non.my_mall.dto;

import com.non.my_mall.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class PmsBrandParams {
    @NotNull(message = "品牌名称不能为空")
    @ApiModelProperty(value = "品牌名称",required = true)
    private String name;

    @NotNull(message = "品牌首字母不能为空")
    @ApiModelProperty(value = "品牌首字母")
    private String firstLetter;


    @Min(value = 0)
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @NotNull
    @FlagValidator(value = {"0","1"}, message = "厂家状态不正确")
    @ApiModelProperty(value = "是否为厂家制造商")
    private Integer factoryStatus;

    @ApiModelProperty(value = "是否进行显示")
    private Integer showStatus;


    @ApiModelProperty(value = "品牌logo",required = true)
    private String logo;

    @ApiModelProperty(value = "品牌大图")
    private String bigPic;
    @ApiModelProperty(value = "品牌故事")
    private String brandStory;
}
