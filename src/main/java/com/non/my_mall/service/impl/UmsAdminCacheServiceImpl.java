package com.non.my_mall.service.impl;

import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.mbg.model.UmsResource;
import com.non.my_mall.service.RedisService;
import com.non.my_mall.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATEBASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;
    @Override
    public void delAdmin(Long adminId) {

    }

    @Override
    public void delResourceList(Long adminId) {

    }

    @Override
    public void delResourceListByRole(Long roleId) {

    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {

    }

    @Override
    public void delResourceListByResource(Long resourceId) {

    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATEBASE + ":" + REDIS_KEY_ADMIN + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATEBASE + ":" + REDIS_KEY_ADMIN + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {

    }
}
