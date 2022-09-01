package com.non.my_mall.dto;

import lombok.Data;

@Data
public class SmsHomeRecommendProductParams extends PageParams{
    private String productName;
    private int recommendStatus;
}
