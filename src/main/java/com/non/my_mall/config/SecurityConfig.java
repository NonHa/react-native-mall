package com.non.my_mall.config;

import com.non.my_mall.component.JwtAuthenticationTokenFilter;
import com.non.my_mall.component.RestAuthenticationEntryPoint;
import com.non.my_mall.component.RestfulAccessDeniedHandler;
import com.non.my_mall.service.CustomUserDetailService;
import com.non.my_mall.service.UmsAdminRoleRelationService;
import com.non.my_mall.service.UmsAdminService;
import com.non.my_mall.utils.filter.MyAuthenticationFailureHandler;
import com.non.my_mall.utils.filter.MyAuthenticationFilter;
import com.non.my_mall.utils.filter.MyAuthenticationProvider;
import com.non.my_mall.utils.filter.MyAuthenticationSucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * SpringSecurity的配置
 * Created by macro on 2018/4/26.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private UmsAdminRoleRelationService umsAdminRoleRelationDao;

    //自定义的登录成功时的处理器
    @Autowired
    private MyAuthenticationSucessHandler authenticationSuccessHandler;
//
    //自定义的登录失败时的处理器
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    //自定义的无权限时的处理器
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    //自定义的退出成功时的处理器
//    @Autowired
//    private LogoutSuccessHandler logoutSuccessHandler;


    //这个为前置过滤器，不了解学习之前的博客
//    @Autowired
//    private AuthFilter authFilter;

    //注入我们自定义的后台管理的UserDetailService
    @Autowired
    @Qualifier("adminUserDetailService")
    private CustomUserDetailService adminUserDetailService;

    //注入我们自定义的前台的UserDetailService

    @Autowired
    @Qualifier("appUserDetailService")
    private CustomUserDetailService appUserDetailService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf()// 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                .antMatchers("/admin/login", "/admin/register", "/sso/*")// 对 后台用户和 app会员登录注册要允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
//                .antMatchers("/**")//测试时全部运行访问
//                .permitAll()
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();


        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //用自定义AbstractAuthenticationProcessingFilter覆盖UsernamePasswordAuthenticationFilte
        httpSecurity.addFilterAt(authentication(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //创建一个自定义的AbstractUserDetailsAuthenticationProvider对象
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        //把我们自定义的userDetailService，放入这个对象
        List<CustomUserDetailService> userDetailServices = new ArrayList<>();
        userDetailServices.add(adminUserDetailService);
        userDetailServices.add(appUserDetailService);
        myAuthenticationProvider.setUserDetailsServices(userDetailServices);
        myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        //配置我们自定义的AbstractUserDetailsAuthenticationProvider
        auth.authenticationProvider(myAuthenticationProvider);

//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
    }

    //注入我们自定义的AbstractAuthenticationProcessingFilter
    @Bean
    public MyAuthenticationFilter authentication() throws Exception {
        //此处生成一个自定义的AbstractAuthenticationProcessingFilter对象，并配置登录请求的路径
        MyAuthenticationFilter myAuthenticationFilter = new MyAuthenticationFilter("/admin/login");
        myAuthenticationFilter.setAuthenticationManager(this.authenticationManager());

        //登录成功时处理器
        myAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        //登录失败时的处理器
        myAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return myAuthenticationFilter;
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        //指定允许跨域的请求(*所有)：http://wap.ivt.guansichou.com
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "X-User-Agent", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        //获取登录用户信息
//        return username -> {
//            UmsAdmin admin = adminService.getAdminByUsername(username);
//
//            if (admin != null) {
//                List<UmsPermission> permissionList = umsAdminRoleRelationDao.getPermissionList(admin.getId());
//
//                return new AdminUserDetails(admin,permissionList);
//            }
//            throw new UsernameNotFoundException("用户名或密码错误");
//        };
//    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
