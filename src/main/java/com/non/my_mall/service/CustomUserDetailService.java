package com.non.my_mall.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface CustomUserDetailService extends UserDetailsService {
    //该方法需自定义的UserDetailsService实现，表示该UserDetailsService匹配什么平台
    Boolean supports(String platform);

}
