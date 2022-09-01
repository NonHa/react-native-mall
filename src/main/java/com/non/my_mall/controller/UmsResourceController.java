package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.UmsResourceParam;
import com.non.my_mall.mbg.model.UmsResource;
import com.non.my_mall.service.UmsResourceService;
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
@Api(tags = "资源列表")
@RequestMapping(value = "/resource")
public class UmsResourceController {
    @Autowired
    private UmsResourceService resourceService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "列表")
    @ResponseBody
    public CommonResult<CommonPage<UmsResource>> getList(@RequestBody UmsResourceParam param) {
        List<UmsResource> list = resourceService.getList(param);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "更新")
    @ResponseBody
    public CommonResult updateById(@RequestBody UmsResource param) {
        int count = resourceService.updateById(param);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "新增")
    @ResponseBody
    public CommonResult addCate(@RequestBody UmsResource param) {
        int count = resourceService.addCate(param);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }

    }
}
