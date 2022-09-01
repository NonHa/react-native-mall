package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.SmsCouponProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsCouponProductRelationDao {
    List<SmsCouponProductRelation> getList(Long coupId);
    int insert(@Param(value = "list") List<SmsCouponProductRelation> param);
    int delete(Long coupId);
}
