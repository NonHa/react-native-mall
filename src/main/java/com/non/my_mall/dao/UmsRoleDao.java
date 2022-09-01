package com.non.my_mall.dao;

import com.non.my_mall.dto.UmsRoleParam;
import com.non.my_mall.mbg.model.UmsMenu;
import com.non.my_mall.mbg.model.UmsRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UmsRoleDao {
    List<UmsRole> getLIst(UmsRoleParam param);
    int updateRole(UmsRole param);
    int addRole(UmsRole param);

    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);

    List<UmsMenu> getMenuListByAdminId(@Param("adminId") Long adminId);
}
