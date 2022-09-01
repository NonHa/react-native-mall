package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.SmsFlashSessionDetail;
import com.non.my_mall.mbg.model.SmsFlashPromotionSession;
import com.non.my_mall.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "促销时间段配置")
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {
    @Autowired
    private SmsFlashPromotionSessionService flashPromotionSessionService;

    @ApiOperation("列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<SmsFlashPromotionSession>> getList() {
        List<SmsFlashPromotionSession> list = flashPromotionSessionService.getList();
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody SmsFlashPromotionSession flashPromotionSession) {
        int i = flashPromotionSessionService.updateSession(flashPromotionSession);
        if (i> 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody SmsFlashPromotionSession flashPromotionSession) {
        int i = flashPromotionSessionService.add(flashPromotionSession);
        if (i> 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("获取分配 商品列表 时间段")
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsFlashSessionDetail>> selectList(@RequestParam Long flashPromotionId) {
        List<SmsFlashSessionDetail> smsFlashSessionDetails = flashPromotionSessionService.selectList(flashPromotionId);
        return CommonResult.success(CommonPage.restPage(smsFlashSessionDetails));
    }
}
