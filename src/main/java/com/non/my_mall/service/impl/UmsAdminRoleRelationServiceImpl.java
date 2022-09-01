package com.non.my_mall.service.impl;

import com.non.my_mall.dao.UmsAdminRoleRelationDao;
import com.non.my_mall.mbg.mapper.UmsAdminRoleRelationMapper;
import com.non.my_mall.mbg.model.UmsAdminRoleRelation;
import com.non.my_mall.mbg.model.UmsPermission;
import com.non.my_mall.mbg.model.UmsResource;
import com.non.my_mall.mbg.model.UmsRole;
import com.non.my_mall.service.UmsAdminRoleRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UmsAdminRoleRelationServiceImpl implements UmsAdminRoleRelationService {
    @Resource
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    @Override
    public int insertList(List<UmsAdminRoleRelation> adminRoleRelationList) {
        return 0;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return null;
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    @Override
    public List<Long> getAdminIdList(Long resourceId) {
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationMapper.getPermissionList(adminId);
    }

}
