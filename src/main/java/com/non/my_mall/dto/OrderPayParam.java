package com.non.my_mall.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderPayParam {
    private Long orderId;
    private List<Long> orderItemIds;
}
