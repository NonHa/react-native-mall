package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.PmsProductAttribute;
import com.non.my_mall.mbg.model.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    @Getter
    @Setter
    @ApiModelProperty(value = "商品属性列表")
    public List<PmsProductAttribute> productAttributeList;
}
