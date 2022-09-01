package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.UmsMenuNode;
import com.non.my_mall.dto.UmsMenuParam;
import com.non.my_mall.mbg.model.UmsMenu;
import com.non.my_mall.service.UmsMenuService;
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
@Api(tags = "菜单配置")
@RequestMapping(value = "/menu")
public class UmsMenuController {
    @Autowired
    private UmsMenuService menuService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "菜单列表")
    @ResponseBody
    public CommonResult<CommonPage<UmsMenu>> getList(@RequestBody UmsMenuParam param) {
        List<UmsMenu> list = menuService.getList(param);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ApiOperation(value = "菜单列表 分级")
    @ResponseBody
    public CommonResult<CommonPage<UmsMenuNode>> getTreeList() {
        List<UmsMenuNode> trees = menuService.getTrees();
        return CommonResult.success(CommonPage.restPage(trees));
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "菜单更新")
    @ResponseBody
    public CommonResult update(@RequestBody UmsMenu param) {
        int count = menuService.update(param);
        if (count > 0 ) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "菜单-新增")
    @ResponseBody
    public CommonResult add(@RequestBody UmsMenu param) {
        int count = menuService.add(param);
        if (count > 0 ) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }

    }
}
