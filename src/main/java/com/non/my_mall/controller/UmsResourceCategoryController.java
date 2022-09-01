package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.UmsResourceCategoryParam;
import com.non.my_mall.dto.UmsResourceCategoryWithChildren;
import com.non.my_mall.mbg.model.UmsResourceCategory;
import com.non.my_mall.service.UmsResourceCategoryService;
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
@Api(tags = "资源分类")
@RequestMapping("/resource/category")
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation(value = "列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<UmsResourceCategory>> getListAll(@RequestBody UmsResourceCategoryParam param) {
        List<UmsResourceCategory> list = resourceCategoryService.getListAll(param);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation(value = "列表-分级")
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<UmsResourceCategoryWithChildren>> getTree() {
        List<UmsResourceCategoryWithChildren> tree = resourceCategoryService.getTree();
        return CommonResult.success(CommonPage.restPage(tree));
    }
}
