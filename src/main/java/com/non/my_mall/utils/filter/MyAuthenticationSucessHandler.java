package com.non.my_mall.utils.filter;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.non.my_mall.dto.SecurityUser;
import com.non.my_mall.mbg.model.UmsAdmin;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        UmsAdmin currentUserInfo = principal.getCurrentUserInfo();
        currentUserInfo.setPassword(null);
        System.out.println("authentication==>"+ principal);
//        System.out.println("authentication getDetails"+authentication.getDetails());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token", principal.getToken());
        map.put("info", currentUserInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(JSONObject.parseObject(JSON.toJSONString(map))));

    }
}
