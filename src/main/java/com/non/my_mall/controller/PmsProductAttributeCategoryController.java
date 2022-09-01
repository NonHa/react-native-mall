package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.PmsProductAttributeCategoryItem;
import com.non.my_mall.mbg.model.PmsProductAttributeCategory;
import com.non.my_mall.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(
            PmsProductAttributeCategory params,
            @RequestParam(defaultValue = "5",value = "page") Integer pageNum,  @RequestParam(defaultValue = "1") Integer pageSize) {
        List<PmsProductAttributeCategory> list = productAttributeCategoryService.getList(params,pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @ApiOperation("分页获取所有商品属性分类-创建")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody @Validated PmsProductAttributeCategory name) {
        int count = productAttributeCategoryService.creatProductAttrCategory(name);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }

    }
    @ApiOperation("分页获取所有商品属性分类-删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.deleteById(id);
        if (count > 0) {
            return  CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取联级列表")
    @RequestMapping(value = "/getAttrList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductAttributeCategoryItem>> delete() {
        List<PmsProductAttributeCategoryItem> attrWith = productAttributeCategoryService.getAttrWith();
       return CommonResult.success(CommonPage.restPage(attrWith));
    }
}
