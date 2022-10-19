package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.OmsCartItem;
import lombok.Data;

import java.util.List;

@Data
public class OmsCarItemListDetail extends OmsCartItem {
    private List<OmsCartItem> productList;
}
