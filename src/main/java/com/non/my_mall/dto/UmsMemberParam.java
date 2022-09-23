package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.UmsMember;
import com.non.my_mall.mbg.model.UmsMemberStatisticsInfo;
import lombok.Data;

@Data
public class UmsMemberParam extends UmsMember {
    private UmsMemberStatisticsInfo detail;
    private String levelName;
}
