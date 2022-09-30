package com.non.my_mall.component;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.non.my_mall.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 自定义返回结果：未登录或登录过期
 * Created by macro on 2018/5/14.
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("RestAuthenticationEntryPoint");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("message", authException.getMessage());
        response.setContentType("application/json;charset=utf-8");
        System.out.println("map"+map);
        response.getWriter().write(JSONUtil.toJsonStr(JSONObject.parseObject(JSON.toJSONString(map))));
//        response.getWriter().flush();
    }
}
