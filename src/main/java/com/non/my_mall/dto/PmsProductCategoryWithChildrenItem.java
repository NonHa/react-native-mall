package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.PmsProduct;
import com.non.my_mall.mbg.model.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含子级分类的商品分类
 * Created by macro on 2018/5/25.
 */
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    @Getter
    @Setter
    @ApiModelProperty("子集分类")
    private List<PmsProductCategory> children;
}
