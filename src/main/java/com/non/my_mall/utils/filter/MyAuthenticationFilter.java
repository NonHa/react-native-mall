package com.non.my_mall.utils.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.micrometer.core.lang.Nullable;
import org.apache.http.protocol.HTTP;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private boolean postOnly = true;

    public MyAuthenticationFilter() {
        super(new AntPathRequestMatcher("/admin/login", "POST"));
    }

    public MyAuthenticationFilter(String loginProcessingUrl) {
        super(new AntPathRequestMatcher(loginProcessingUrl, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
// 读取请求 application/json  请求体中的 JSON 内容  如果是 form-data content 请求 则直接从 request 中获取参数
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine())!=null){
                sb.append(line);
            }

            // 将资料解码
            String reqBody = sb.toString();
            String username = obtainUsername(reqBody);
            String password = obtainPassword(reqBody);

            System.out.println("username=="+username);
            System.out.println("password=="+password);


            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }
            System.out.println("request.getParameter==>"+ request.getParameter("platform"));
            username = username.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);

            // 此处从请求中获取所有的参数和请求头放入UsernamePasswordAuthenticationToken
            setDetails(request, authRequest,reqBody);

            return this.getAuthenticationManager().authenticate(authRequest);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Nullable
    protected String obtainUsername(String obj) {


        return JSONObject.parseObject(obj).getString(usernameParameter);

    }

    @Nullable
    protected String obtainPassword(String obj) {
        return JSONObject.parseObject(obj).getString(passwordParameter);
    }

    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest,String bodyValue) {
        Map<String, Object> params = new HashMap<>();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        if (!CollectionUtils.isEmpty(parameterMap)) {
//            params.putAll(parameterMap);
//            params.put("platform", request.getParameter("platform"));
//        }
        Map<String, String> parse = (Map) JSON.parse(bodyValue);

        for (Map.Entry<String, String> entry : parse.entrySet()) {
            params.put(entry.getKey(),entry.getValue());
        }

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            params.put(key, value);
        }
        System.out.println("parse"+params);
        authRequest.setDetails(params);
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public String getRequestJSONBodyKeyValue(HttpServletRequest request, String key) {
        // 读取请求 application/json  请求体中的 JSON 内容  如果是 form-data content 请求 则直接从 request 中获取参数
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine())!=null){
                sb.append(line);
            }

            // 将资料解码
            String reqBody = sb.toString();
            System.out.println("request=="+ JSONObject.parseObject(reqBody).getString(key));
            return JSONObject.parseObject(reqBody).getString(key);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
