package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.OmsOrderParams;
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
}
