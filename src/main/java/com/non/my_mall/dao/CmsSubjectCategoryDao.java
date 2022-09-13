package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.CmsSubjectCategory;

import java.util.List;

public interface CmsSubjectCategoryDao {
    List<CmsSubjectCategory> getList();
    int addCategory(CmsSubjectCategory param);
}
