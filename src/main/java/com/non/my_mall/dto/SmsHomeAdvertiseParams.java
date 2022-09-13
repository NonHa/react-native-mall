package com.non.my_mall.dto;

import lombok.Data;

@Data
public class SmsHomeAdvertiseParams extends PageParams{
    private String name;
    private Integer type;
    private String status;
}
