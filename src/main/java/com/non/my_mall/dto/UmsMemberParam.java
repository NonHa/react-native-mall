package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.PmsProductCategory;
import com.non.my_mall.mbg.model.UmsMember;
import com.non.my_mall.mbg.model.UmsMemberStatisticsInfo;
import lombok.Data;

import java.util.List;

@Data
public class UmsMemberParam extends UmsMember {
    private UmsMemberStatisticsInfo detail;
    private String levelName;
    private List<PmsProductCategory> categoryList;
}
