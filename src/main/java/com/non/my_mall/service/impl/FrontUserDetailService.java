package com.non.my_mall.service.impl;

import com.non.my_mall.dto.SecurityUser;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.mbg.model.UmsMember;
import com.non.my_mall.service.CustomUserDetailService;
import com.non.my_mall.service.UmsMemeberService;
import com.non.my_mall.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("frontUserDetailService")
public class FrontUserDetailService implements CustomUserDetailService {
    private final String PLAT_FORM = "front";

    @Autowired
    private UmsMemeberService memeberService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public Boolean supports(String platform) {
        return PLAT_FORM.equals(platform);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("front");
        List<UmsMember> member = memeberService.getMember(s);
        if (member.size() > 0) {
            UmsAdmin umsAdmin = new UmsAdmin();
            UmsMember item = member.get(0);
            umsAdmin.setUsername(item.getUsername());
            umsAdmin.setPassword(item.getPassword());
            SecurityUser securityUser = new SecurityUser(umsAdmin);
            String token = jwtTokenUtil.generateToken(securityUser);
            securityUser.setToken(token);
            return securityUser;

        }
        throw new UsernameNotFoundException("用户名或密码错误");

    }
}
