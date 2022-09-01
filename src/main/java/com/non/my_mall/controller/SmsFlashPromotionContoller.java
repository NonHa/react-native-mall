package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.SmsFlashPromotionParam;
import com.non.my_mall.mbg.model.SmsFlashPromotion;
import com.non.my_mall.service.SmsFlashPromotionService;
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
@Api(tags = "营销")
@RequestMapping(value = "/flash")
public class SmsFlashPromotionContoller {
    @Autowired
    private SmsFlashPromotionService flashPromotionService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "秒杀活动列表")
    @ResponseBody
    public CommonResult<CommonPage<SmsFlashPromotion>> getList(@RequestBody SmsFlashPromotionParam param) {
        List<SmsFlashPromotion> flashList = flashPromotionService.getFlashList(param);
        return CommonResult.success(CommonPage.restPage(flashList));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "秒杀活动-更新")
    @ResponseBody
    public CommonResult update(@RequestBody SmsFlashPromotion param) {
        int i = flashPromotionService.updateFlashById(param);
       if (i > 0) {
           return CommonResult.success(null);
       } else {
           return CommonResult.failed("操作失败");
       }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "秒杀活动-新增")
    @ResponseBody
    public CommonResult add(@RequestBody SmsFlashPromotion param) {
        int i = flashPromotionService.addFlash(param);
        if (i > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }
}
