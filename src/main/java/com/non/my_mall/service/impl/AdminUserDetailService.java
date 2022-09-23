package com.non.my_mall.service.impl;

import com.non.my_mall.dto.SecurityUser;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.service.CustomUserDetailService;
import com.non.my_mall.service.UmsAdminService;
import com.non.my_mall.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("adminUserDetailService")
@Primary
public class AdminUserDetailService implements CustomUserDetailService {
    private final String PLAT_FORM = "admin";

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisServiceImpl redisService;
    @Override
    public Boolean supports(String platform) {
        return PLAT_FORM.equals(platform);
    }

    @Override
    public SecurityUser loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("s=="+s);

        UmsAdmin adminByUsername = adminService.getAdminByUsername(s);
        System.out.println("adminByUsername=="+adminByUsername);
        if (adminByUsername != null) {
            System.out.println("there is admin==>"+adminByUsername.getPassword());
            SecurityUser securityUser = new SecurityUser(adminByUsername);
            String token = jwtTokenUtil.generateToken(securityUser);
            securityUser.setToken(token);

            redisService.set(adminByUsername.getUsername(), token);
            redisService.set("platform", "admin");
            return securityUser;
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

}
