package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.service.UmsMemeberService;
import com.non.my_mall.service.impl.UmsMemberServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
public class UmsMemberController {
}
//@Controller
//@Api(tags = "UmsMemberController", description = "会员登录注册管理")
//@RequestMapping("/sso")
//public class UmsMemberController {
//
//    @Autowired
//    private UmsMemeberService memeberService;
//    @ApiOperation("获取验证码")
//    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult getAuthCode(@RequestParam String telephone) {
//        return memeberService.generateAuthCode(telephone);
//    }
//
//    @ApiOperation("判断验证码是否正确")
//    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult updatePassword(@RequestParam String telephone,
//                                       @RequestParam String authCode) {
//        System.out.println("telephone="+ telephone);
//        return memeberService.verifyAuthCode(telephone,authCode);
//    }
//}
