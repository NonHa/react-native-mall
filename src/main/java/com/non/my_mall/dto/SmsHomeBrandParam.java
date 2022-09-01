package com.non.my_mall.dto;

import lombok.Data;

@Data
public class SmsHomeBrandParam extends PageParams{
    private String brandName;
    private int recommendStatus;

}
