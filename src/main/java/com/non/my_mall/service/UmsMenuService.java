package com.non.my_mall.service;

import com.non.my_mall.dto.UmsMenuNode;
import com.non.my_mall.dto.UmsMenuParam;
import com.non.my_mall.mbg.model.UmsMenu;

import java.util.List;

public interface UmsMenuService {
    List<UmsMenu> getList(UmsMenuParam params);
    int update(UmsMenu params);
    int add(UmsMenu params);
    List<UmsMenuNode> getTrees();
}
