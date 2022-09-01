package com.non.my_mall.controller;


import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.SmsFlashPromotionProduct;
import com.non.my_mall.mbg.model.SmsFlashPromotionProductRelation;
import com.non.my_mall.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "SmsFlashPromotionProductRelationController")
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {

    @Autowired
    private SmsFlashPromotionProductRelationService productRelationService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增")
    public CommonResult getList(@RequestBody List<SmsFlashPromotionProductRelation> param) {
        int i = productRelationService.create(param);
        if (i > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "列表")
    public CommonResult<CommonPage<SmsFlashPromotionProduct>> getList(@RequestParam Long promotionId,
                                                                      @RequestParam Long sessionId
                                                                      ) {
        List<SmsFlashPromotionProduct> list = productRelationService.getList(promotionId, sessionId);
        return CommonResult.success(CommonPage.restPage(list));
    }


    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "删除")
    public CommonResult delete(@RequestParam Long id) {
        int i = productRelationService.delete(id);
        if (i > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新")
    public CommonResult update(@RequestBody SmsFlashPromotionProduct param) {
        int i = productRelationService.update(param);
        if (i > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }
}
