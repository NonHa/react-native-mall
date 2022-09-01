package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.UmsRoleDao;
import com.non.my_mall.dao.UmsRoleMenuRalationDao;
import com.non.my_mall.dao.UmsRoleResourceRalationDao;
import com.non.my_mall.dto.UmsRoleParam;
import com.non.my_mall.mbg.model.UmsMenu;
import com.non.my_mall.mbg.model.UmsResource;
import com.non.my_mall.mbg.model.UmsRole;
import com.non.my_mall.service.UmsRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Resource
    private UmsRoleDao roleDao;

    @Resource
    private UmsRoleMenuRalationDao roleMenuRalationDao;

    @Resource
    private UmsRoleResourceRalationDao roleResourceRalationDao;
    @Override
    public int create(UmsRole role) {
        UmsRole umsRole = new UmsRole();
        BeanUtil.copyProperties(role,umsRole);
        umsRole.setCreateTime(new Date());
        umsRole.setAdminCount(0);
        return roleDao.addRole(umsRole);
    }

    @Override
    public int update(Long id, UmsRole role) {
        return roleDao.updateRole(role);
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public List<UmsRole> list() {
        return null;
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<UmsRole> list(UmsRoleParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        return roleDao.getLIst(param);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        List<UmsMenu> menuListByAdminId = roleDao.getMenuListByAdminId(adminId);
        return menuListByAdminId;
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {

        List<UmsMenu> menuListByRoleId = roleMenuRalationDao.getMenuListByRoleId(roleId);

        return menuListByRoleId;
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        List<UmsResource> menuListByRoleId = roleResourceRalationDao.getMenuListByRoleId(roleId);
        return menuListByRoleId;
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        roleMenuRalationDao.deleteRalationByRoleId(roleId);
        for (Long i: menuIds) {
            roleMenuRalationDao.insertRoleMenuRelation(roleId, i);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {

        roleResourceRalationDao.deleteRalationByRoleId(roleId);
        for (Long i: resourceIds) {
            roleResourceRalationDao.insertRoleMenuRelation(roleId, i);
        }
        return resourceIds.size();
    }
}
