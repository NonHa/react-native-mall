package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.OmsOrder;
import com.non.my_mall.mbg.model.OmsOrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OmsOrderDetail extends OmsOrder {
    private List<OmsOrderItem> orderItemList;
}
