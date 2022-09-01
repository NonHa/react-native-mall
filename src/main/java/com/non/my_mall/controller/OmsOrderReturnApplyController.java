package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.OmsOrderReturnApplyParam;
import com.non.my_mall.dto.OmsUpdateParam;
import com.non.my_mall.mbg.model.OmsOrderReturnApply;
import com.non.my_mall.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "退货列表")
@RequestMapping(value = "/order/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyService orderReturnApplyService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderReturnApply>> getList(@RequestBody OmsOrderReturnApplyParam param) {
        List<OmsOrderReturnApply> list = orderReturnApplyService.getList(param);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody OmsUpdateParam param) {
        int update = orderReturnApplyService.update(param);
        if (update > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }
}
