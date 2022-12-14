package com.non.my_mall.service;

import com.non.my_mall.dto.PmsProdcutInfoDetail;
import com.non.my_mall.dto.PmsProductQueryParam;
import com.non.my_mall.mbg.model.PmsComment;
import com.non.my_mall.mbg.model.PmsProduct;

import java.util.List;

public interface PmsProductService {
    /**
     * 分页查询商品
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);
    List<PmsProduct> list(String keyword);
    PmsProdcutInfoDetail getProductById(Long id);
    List<PmsComment> getCommentById(Long id, int pageNum, int pageSize);
}
