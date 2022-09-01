package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.SmsCouponProductCategoryRelation;
import com.non.my_mall.mbg.model.SmsCouponProductRelation;

import java.util.List;

public interface SmsCouponProductCategoryRelationDao {
    List<SmsCouponProductCategoryRelation> getList(Long coupId);
    int insert(List<SmsCouponProductCategoryRelation> param);
    int delete(Long coupId);
}
