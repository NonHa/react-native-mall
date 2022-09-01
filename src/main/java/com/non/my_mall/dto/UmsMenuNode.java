package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.UmsMenu;
import lombok.Data;

import java.util.List;

@Data
public class UmsMenuNode extends UmsMenu {
    private List<UmsMenu> children;
}
