package com.non.my_mall.dao;

import com.non.my_mall.dto.UmsAdminParam;
import com.non.my_mall.mbg.model.UmsAdmin;

import java.util.List;

public interface UmsAdminDao {
    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UmsAdmin> list(UmsAdminParam param);
    int update(UmsAdmin admin);
    int insertUser(UmsAdmin admin);
}
