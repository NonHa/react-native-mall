package com.non.my_mall.service;

import com.non.my_mall.common.api.CommonResult;

public interface UmsMemeberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
