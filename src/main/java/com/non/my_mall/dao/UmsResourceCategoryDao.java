package com.non.my_mall.dao;

import com.non.my_mall.dto.UmsResourceCategoryParam;
import com.non.my_mall.dto.UmsResourceCategoryWithChildren;
import com.non.my_mall.mbg.model.UmsResourceCategory;

import java.util.List;

public interface UmsResourceCategoryDao {
    List<UmsResourceCategory> getListAll(UmsResourceCategoryParam param);
    List<UmsResourceCategoryWithChildren> getTree();
}
