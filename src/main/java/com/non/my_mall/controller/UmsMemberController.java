package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.mbg.model.UmsMember;
import com.non.my_mall.service.UmsMemeberService;
import com.non.my_mall.service.impl.UmsMemberServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemeberService memeberService;

    @ApiOperation("新增会员")
    @RequestMapping(value = "/addMemeber", method = RequestMethod.POST)
    @ResponseBody
    public UmsMember addMemeber(@RequestBody UmsMember param) {
        return memeberService.addMemeber(param);
    }

    @ApiOperation("更新会员信息")
    @RequestMapping(value = "/updateMemeber", method = RequestMethod.POST)
    @ResponseBody
    public int updateMemeber(@RequestBody UmsMember param) {
        return memeberService.updateMember(param);
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return memeberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        System.out.println("telephone="+ telephone);
        return memeberService.verifyAuthCode(telephone,authCode);
    }

    @ApiOperation("获取会员信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public UmsMember getMemeber(Principal principal) {
        List<UmsMember> member = memeberService.getMember(principal.getName());
        if (member.size() > 0) {
            return member.get(0);
        }
        return null;
    }

}
