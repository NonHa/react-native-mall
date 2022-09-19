package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.UmsMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsMemberDao {
    /**
     * 新增会员用户
     * */
    int addMemeber(UmsMember param);

    List<UmsMember> getMember(@Param("phone") String phone);

}
