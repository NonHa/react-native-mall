package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.SmsHomeAdvertiseParams;
import com.non.my_mall.mbg.model.SmsHomeAdvertise;
import com.non.my_mall.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home/advertise")
@Api(tags = "广告列表")
public class SmsHomeAdvertiseContoller {

    @Autowired
    private SmsHomeAdvertiseService homeAdvertiseService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonPage<SmsHomeAdvertise> getList(@RequestBody SmsHomeAdvertiseParams params) {
        List<SmsHomeAdvertise> list = homeAdvertiseService.getList(params);
        return CommonPage.restPage(list);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public int update(@RequestBody SmsHomeAdvertise params) {
        int i = homeAdvertiseService.updateById(params);
        return i;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody SmsHomeAdvertise params) {
        int i = homeAdvertiseService.addItem(params);
        return i;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public int delete(@RequestParam("id") Long id) {
        int i = homeAdvertiseService.deleteById(id);
        return i;
    }
}
