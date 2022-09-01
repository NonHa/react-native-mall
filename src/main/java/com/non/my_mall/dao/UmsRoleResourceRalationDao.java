package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.UmsMenu;
import com.non.my_mall.mbg.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleResourceRalationDao {
    int deleteRalationByRoleId(Long roleId);
    int insertRoleMenuRelation(@Param("roleId") Long roleId, @Param("sourceId") Long sourceId);
    List<UmsResource> getMenuListByRoleId(@Param("roleId") Long roleId);
}
