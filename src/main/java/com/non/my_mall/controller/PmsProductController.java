package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.PmsProductQueryParam;
import com.non.my_mall.mbg.model.PmsProduct;
import com.non.my_mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping(value = "product")
public class PmsProductController {
    @Autowired
    private PmsProductService productService;


    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> getList(@RequestBody  PmsProductQueryParam productQueryParam ) {

        List<PmsProduct> productList = productService.list(productQueryParam, productQueryParam.getPageSize(), productQueryParam.getPage());
        return CommonResult.success(CommonPage.restPage(productList));
    }
    @ApiOperation("查询商品")
    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> getList(@RequestParam String keyword ) {

        List<PmsProduct> list = productService.list(keyword);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @ApiOperation("批量修改删除状态")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
