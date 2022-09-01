package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductCategoryAndAttributeRelationDao {

    int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAndAttributeRelationDaos);
}
