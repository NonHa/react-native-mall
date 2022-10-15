package com.non.my_mall.service;

import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.SecurityUser;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.mbg.model.UmsMember;

import java.util.List;

public interface UmsMemeberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

    /**
     * 新增会员用户
     * */
    UmsMember addMemeber(UmsMember param);

    List<UmsMember> getMember(String phone);

    int updateMember(UmsMember param);

    UmsAdmin getCurrentMember();
}
