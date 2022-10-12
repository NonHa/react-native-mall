package com.non.my_mall.dao;

import com.non.my_mall.dto.PmsProdcutInfoDetail;
import com.non.my_mall.mbg.model.PmsComment;
import com.non.my_mall.mbg.model.PmsProduct;

import java.util.List;

public interface PmsProductDao {
    List<PmsProduct> getSimpleList(String keyword);
    PmsProdcutInfoDetail getProductById(Long id);
    List<PmsComment> getCommentById(Long id);

}
