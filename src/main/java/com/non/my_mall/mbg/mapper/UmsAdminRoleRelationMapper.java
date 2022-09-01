package com.non.my_mall.mbg.mapper;

import com.non.my_mall.dao.UmsAdminRoleRelationDao;
import com.non.my_mall.mbg.model.UmsAdminRoleRelation;
import com.non.my_mall.mbg.model.UmsAdminRoleRelationExample;
import java.util.List;

import com.non.my_mall.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminRoleRelationMapper {
    long countByExample(UmsAdminRoleRelationExample example);

    int deleteByExample(UmsAdminRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelation row);

    int insertSelective(UmsAdminRoleRelation row);

    List<UmsAdminRoleRelation> selectByExample(UmsAdminRoleRelationExample example);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsAdminRoleRelation row, @Param("example") UmsAdminRoleRelationExample example);

    int updateByExample(@Param("row") UmsAdminRoleRelation row, @Param("example") UmsAdminRoleRelationExample example);

    int updateByPrimaryKeySelective(UmsAdminRoleRelation row);

    int updateByPrimaryKey(UmsAdminRoleRelation row);
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);


}