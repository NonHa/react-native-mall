package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.SmsCouponParam;
import com.non.my_mall.dto.SmsCouponRalationParam;
import com.non.my_mall.mbg.model.SmsCoupon;
import com.non.my_mall.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "优惠券")
@RequestMapping("/coupon")
public class SmsCouponController {
    @Autowired
    private SmsCouponService couponService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "列表")
    @ResponseBody
    public CommonResult<CommonPage<SmsCoupon>> getList(@RequestBody SmsCouponParam param) {
        List<SmsCoupon> list = couponService.getList(param);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public CommonResult delete(@RequestParam Long id) {
        int count = couponService.delete(id);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "更新")
    @ResponseBody
    public CommonResult update(@RequestBody SmsCouponRalationParam param) {
        int count = couponService.update(param);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "列表")
    @ResponseBody
    public CommonResult insert(@RequestBody SmsCouponRalationParam param) {
        int count = couponService.insert(param);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }
}
