package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.OmsOrderReturnReasonParam;
import com.non.my_mall.mbg.model.OmsOrderReturnApply;
import com.non.my_mall.mbg.model.OmsOrderReturnReason;
import com.non.my_mall.service.OmsOrderReturnReasonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "退货理由")
@RequestMapping(value = "/order/returnReason")
public class OmsOrderReturnReasonController {
    @Autowired
    private OmsOrderReturnReasonService orderReturnReasonService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderReturnReason>> getList(@RequestBody OmsOrderReturnReasonParam param) {
        List<OmsOrderReturnReason> list = orderReturnReasonService.getList(param);

        return CommonResult.success(CommonPage.restPage(list));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getList(@RequestBody OmsOrderReturnReason param) {
        int update = orderReturnReasonService.update(param);
        if (update > 0) {
            return CommonResult.success(null);
        } else {
            return  CommonResult.failed("操作失败");
        }

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addReason(@RequestBody OmsOrderReturnReason param) {
        int update = orderReturnReasonService.addReason(param);
        if (update > 0) {
            return CommonResult.success(null);
        } else {
            return  CommonResult.failed("操作失败");
        }

    }


}
