package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dao.UmsMemberDao;
import com.non.my_mall.dto.SecurityUser;
import com.non.my_mall.mbg.mapper.UmsMemberMapper;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.mbg.model.UmsMember;
import com.non.my_mall.mbg.model.UmsMemberExample;
import com.non.my_mall.service.RedisService;
import com.non.my_mall.service.UmsMemeberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemeberService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private UmsMemberMapper memberMapper;
    @Resource
    private UmsMemberDao memberDao;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public UmsMember addMemeber(UmsMember param) {
        UmsMember umsMember = new UmsMember();
        BeanUtil.copyProperties(param, umsMember);
        umsMember.setCreateTime(new Date());

        UmsMemberExample umsMemberExample = new UmsMemberExample();
        umsMemberExample.createCriteria().andPhoneEqualTo(umsMember.getPhone());
        List<UmsMember> umsMembers = memberMapper.selectByExample(umsMemberExample);
        if (umsMembers.size() > 0) {
            return null;
        }

        String encode = passwordEncoder.encode(umsMember.getPassword());
        umsMember.setPassword(encode);
        memberDao.addMemeber(umsMember);
        return umsMember;
    }

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //????????????????????????????????????redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "?????????????????????");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("??????????????????");
        }
        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return CommonResult.success(null, "?????????????????????");
        } else {
            return CommonResult.failed("??????????????????");
        }
    }

    @Override
    public List<UmsMember> getMember(String phone) {
        return memberDao.getMember(phone);
    }

    @Override
    public int updateMember(UmsMember param) {
        return memberDao.updateMember(param);
    }

    @Override
    public UmsAdmin getCurrentMember() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        System.out.println("principal"+principal);
        return principal.getCurrentUserInfo();
    }
}
