package com.non.my_mall.utils.filter;

import com.non.my_mall.dto.SecurityUser;
import com.non.my_mall.service.CustomUserDetailService;
import com.non.my_mall.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private volatile String userNotFoundEncodedPassword;

    //这里是定制的UserUserDetailService， 分为Admin和Front
    private List<CustomUserDetailService> userDetailsServices;

    private PasswordEncoder passwordEncoder;

    public MyAuthenticationProvider() {
        this.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {

            String presentedPassword = authentication.getCredentials().toString();
            System.out.println("presentedPassword=="+presentedPassword);
            if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                this.logger.debug("Authentication failed: password does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }

    @Override
    protected SecurityUser retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        this.prepareTimingAttackProtection();
        try {
            //在Filter中获取的请求中的所有参数，在此处拿出
            Map detail = (Map) authentication.getDetails();
            System.out.println("detail==>"+detail);

            SecurityUser loadedUser = null;
            //枚举所有自定义的userDetailsService
            for (CustomUserDetailService userDetailsService : userDetailsServices) {
                //在请求中获取平台参数
                Object platform = detail.get("platform");
                //如果不为null则与userDetailsService匹配，配对成功则使用该userDetailsService的loadUserByUsername
                if (Objects.nonNull(platform) && userDetailsService.supports(platform.toString())) {
                    System.out.println("userDetailsService==>"+userDetailsService);
                    loadedUser = (SecurityUser) userDetailsService.loadUserByUsername(username);

                    System.out.println("loadedUser==>"+loadedUser);
                    break;
                }
            }

            //如果为null，没有配对该平台的userDetailsService
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
            } else {

                return loadedUser;
            }
        } catch (UsernameNotFoundException var4) {
            this.mitigateAgainstTimingAttack(authentication);
            throw var4;
        } catch (InternalAuthenticationServiceException var5) {
            throw var5;
        } catch (Exception var6) {
            throw new InternalAuthenticationServiceException(var6.getMessage(), var6);
        }
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
        this.userNotFoundEncodedPassword = null;
    }

    private void prepareTimingAttackProtection() {
        if (this.userNotFoundEncodedPassword == null) {
            this.userNotFoundEncodedPassword = this.passwordEncoder.encode("userNotFoundPassword");
        }
    }

    private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
        if (authentication.getCredentials() != null) {
            String presentedPassword = authentication.getCredentials().toString();
            this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
        }
    }

    public List<CustomUserDetailService> getUserDetailsServices() {
        return userDetailsServices;
    }

    public void setUserDetailsServices(List<CustomUserDetailService> userDetailsServices) {
        this.userDetailsServices = userDetailsServices;
    }
}

