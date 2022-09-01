package com.non.my_mall.dto;

import com.non.my_mall.dto.PageParams;
import lombok.Data;

@Data
public class SmsCouponParam extends PageParams {
    private String name;
    private Integer type;
}
