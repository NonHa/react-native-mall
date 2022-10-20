package com.non.my_mall.controller;

import com.non.my_mall.mbg.model.UmsMemberReceiveAddress;
import com.non.my_mall.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/member/address")
@Api(tags = "用户收货地址")
public class UmsMemberReceiveAddressController {
    @Resource
    private UmsMemberReceiveAddressService memberReceiveAddressService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "地址列表")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getList() {
        return memberReceiveAddressService.getCurrentMemberAddressList();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增地址")
    @ResponseBody
    public int add(@RequestBody UmsMemberReceiveAddress param) {
        return memberReceiveAddressService.addAddress(param);
    }
}
