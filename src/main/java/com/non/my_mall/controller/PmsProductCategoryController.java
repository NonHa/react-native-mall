package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.PmsProductCategoryParams;
import com.non.my_mall.dto.PmsProductCategoryWithChildrenItem;
import com.non.my_mall.mbg.model.PmsProductCategory;
import com.non.my_mall.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping(value = "/productCategory")
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryService productCategoryService;

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<PmsProductCategory>> getList(@RequestBody PmsProductCategory params ) {

        List<PmsProductCategory> list = productCategoryService.getList(params,params.getPage(),params.getPageSize());

        return CommonResult.success(CommonPage.restPage(list));
    }
    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();

        return CommonResult.success(list);
    }

    @ApiOperation("一级分类及子分类-删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed("操作失败！");
        }

    }

    @ApiOperation("-add")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody PmsProductCategoryParams params ) {

        int productCate = productCategoryService.createProductCate(params);

        return CommonResult.success(productCate);
    }

    @ApiOperation("update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody PmsProductCategoryParams params ) {

        int productCate = productCategoryService.updateProductCate(params);

        return CommonResult.success(productCate);
    }
}
