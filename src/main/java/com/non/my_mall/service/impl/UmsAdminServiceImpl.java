package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.UmsAdminDao;
import com.non.my_mall.dao.UmsAdminRoleRelationDao;
import com.non.my_mall.dto.UmsAdminParam;
import com.non.my_mall.mbg.mapper.UmsAdminMapper;
import com.non.my_mall.mbg.model.*;
import com.non.my_mall.service.UmsAdminCacheService;
import com.non.my_mall.service.UmsAdminService;
import com.non.my_mall.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UmsAdminMapper adminMapper;

    @Resource
    private UmsAdminDao adminDao;
    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Resource
    private UmsAdminCacheService adminCacheService;
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if (admin!=null) return admin;
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            adminCacheService.setAdmin(adminList.get(0));
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {

        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtil.copyProperties(umsAdminParam,umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);

        // 查询是否有同名
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(example);
        if (umsAdmins.size() > 0) {
            return null;
        }
        // 密码加密
        String encode = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encode);
        adminDao.insertUser(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            System.out.println("login");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("userDetails=="+userDetails);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return null;
    }

    @Override
    public List<UmsAdmin> list(UmsAdminParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        List<UmsAdmin> list = adminDao.list(param);
        return list;
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        UmsAdmin oldAdmin = adminMapper.selectByPrimaryKey(id);
        if (oldAdmin.getPassword().equals(admin.getPassword())) {
            admin.setPassword(null);
        } else {
            if (StrUtil.isEmpty(admin.getPassword())) {
                admin.setPassword(null);
            } else {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtil.copyProperties(admin, umsAdmin);
        umsAdmin.setId(id);
        return adminDao.update(umsAdmin);
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        adminRoleRelationDao.deleteAdminRoleRelationByROleId(adminId);
        ArrayList<UmsAdminRoleRelation> objects = new ArrayList<>();
        for (Long roleId:  roleIds) {
            UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
            umsAdminRoleRelation.setAdminId(adminId);
            umsAdminRoleRelation.setRoleId(roleId);
            objects.add(umsAdminRoleRelation);
        }
        adminRoleRelationDao.insertList(objects);
        return roleIds.size();
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return null;
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }

//    @Override
//    public List<UmsPermission> getPermissionList(Long adminId) {
//        return adminRoleRelationDao.getPermissionList(adminId);
//    }
}
