package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.*;
import com.non.my_mall.mbg.model.OmsOrder;
import com.non.my_mall.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrder>> getList(@RequestBody OmsOrderParams param) {

        List<OmsOrder> list = orderService.getList(param);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @ApiOperation("查询用户订单")
    @RequestMapping(value = "/itemList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderDetail>> getMemberList(@RequestBody OmsOrderParams param) {

        List<OmsOrderDetail> member = orderService.getMember(param);
        return CommonResult.success(CommonPage.restPage(member));
    }
    @ApiOperation("删除订单")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(Long id) {
        int delete = orderService.delete(id);
        if (delete > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }

    }

    @ApiOperation("新增订单")
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult generateOrder(@RequestBody OmsGenerateOrderParam param) {

        Map<String, Object> generate = orderService.generate(param);
        return CommonResult.success(generate, "生成订单成功");
    }

    @ApiOperation("获取提交订单基础信息")
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult confirmOrder(@RequestBody OmsGenerateOrderParam param) {

        ConfirmOrderResult confirmOrderResult = orderService.generateConfirmOrder(param.getCartIds());
        return CommonResult.success(confirmOrderResult, "");
    }

    @ApiOperation("支付订单")
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult payOrder(@RequestBody OrderPayParam param) {

        Integer integer = orderService.paySuccess(param);
        return CommonResult.success(integer, "");
    }

    @ApiOperation("确认订单")
    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult confirmOrder(@RequestBody OrderPayParam param) {

        Integer integer = orderService.confirmOrder(param.getOrderItemIds());
        return CommonResult.success(integer, "确认成功");
    }
}
