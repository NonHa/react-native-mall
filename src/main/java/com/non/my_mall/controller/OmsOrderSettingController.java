package com.non.my_mall.controller;


import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.mbg.model.OmsOrder;
import com.non.my_mall.mbg.model.OmsOrderSetting;
import com.non.my_mall.service.OmsOrderService;
import com.non.my_mall.service.OmsOrderSettingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "订单设置")
@RequestMapping(value = "/order/setting")
public class OmsOrderSettingController {
    @Autowired
    private OmsOrderSettingService orderSettingService;

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrderSetting> getItem() {
        OmsOrderSetting setting = orderSettingService.getSetting();
        return CommonResult.success(setting);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody OmsOrderSetting param) {
        int i = orderSettingService.updateOrderSetting(param);
        if (i > 0) {
            return  CommonResult.success(null);
        } else {
            return  CommonResult.failed("操作失败");
        }
    }
}
