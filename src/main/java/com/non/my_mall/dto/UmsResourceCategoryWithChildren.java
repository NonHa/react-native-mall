package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.UmsResource;
import com.non.my_mall.mbg.model.UmsResourceCategory;
import lombok.Data;

import java.util.List;

@Data
public class UmsResourceCategoryWithChildren extends  UmsResourceCategory{
    private List<UmsResource> children;
}
