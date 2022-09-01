package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.PmsProduct;

import java.util.List;

public interface PmsProductDao {
    List<PmsProduct> getSimpleList(String keyword);
}
