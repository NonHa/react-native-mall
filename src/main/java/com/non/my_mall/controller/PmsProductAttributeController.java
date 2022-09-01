package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.mbg.model.PmsProductAttribute;
import com.non.my_mall.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cid,
                                                                 @RequestParam(value = "type") Integer type,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                 @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<PmsProductAttribute> list = productAttributeService.getList(cid, type, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据分类查询属性-删除")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@RequestParam Long id) {
        int count = productAttributeService.deleteProductAttributeById(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed("操作失败");
        }

    }

    @ApiOperation("根据分类查询属性-新增")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestBody PmsProductAttribute params) {
        int count = productAttributeService.creatAttribute(params);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed("操作失败");
        }

    }
}
