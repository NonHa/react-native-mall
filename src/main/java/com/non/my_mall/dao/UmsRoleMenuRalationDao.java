package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.UmsMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleMenuRalationDao {
    int deleteRalationByRoleId(Long roleId);
    int insertRoleMenuRelation(@Param("roleId") Long roleId,@Param("menuId") Long menuId);
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

}
