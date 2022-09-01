package com.non.my_mall.service;

import com.non.my_mall.dto.UmsResourceParam;
import com.non.my_mall.mbg.model.UmsResource;

import java.util.List;

public interface UmsResourceService {
    List<UmsResource> getList(UmsResourceParam param);
    int updateById(UmsResource param);
    int addCate(UmsResource param);
}
