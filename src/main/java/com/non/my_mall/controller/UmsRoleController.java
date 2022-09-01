package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.UmsRoleMenuPAram;
import com.non.my_mall.dto.UmsRoleParam;
import com.non.my_mall.dto.UmsRoleResourceParam;
import com.non.my_mall.mbg.model.UmsMenu;
import com.non.my_mall.mbg.model.UmsResource;
import com.non.my_mall.mbg.model.UmsRole;
import com.non.my_mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Api(tags = "角色信息")
@RequestMapping(value = "/role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "列表")
    public CommonResult<CommonPage<UmsRole>> getList(@RequestBody UmsRoleParam param) {
        List<UmsRole> list = roleService.list(param);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新")
    public CommonResult update(@RequestBody UmsRole param) {
        int count = roleService.update(param.getId(), param);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增")
    public CommonResult add(@RequestBody UmsRole param) {
        int count = roleService.create(param);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "建立用户和菜单关系")
    public CommonResult allocMenu(@RequestBody UmsRoleMenuPAram param) {

        int count = roleService.allocMenu(param.getRoleId(),
                Arrays.asList(param.getMenuIds().split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList())
                 );
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }
    @RequestMapping(value = "/allocResource", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "建立用户和资源关系")
    public CommonResult allocResource(@RequestBody UmsRoleResourceParam param) {

        int count = roleService.allocResource(param.getRoleId(),
                Arrays.asList(param.getResourceIds().split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList())
        );
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }
    @RequestMapping(value = "/listMenu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取对应用户配置的菜单")
    public CommonResult<CommonPage<UmsMenu>> listMenu(Long roleId) {

        List<UmsMenu> umsMenus = roleService.listMenu(roleId);
       return CommonResult.success(CommonPage.restPage(umsMenus));
    }

    @RequestMapping(value = "/listResource", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取对应用户配置的资源")
    public CommonResult<CommonPage<UmsResource>> listResource(Long roleId) {

        List<UmsResource> umsResources = roleService.listResource(roleId);
        return CommonResult.success(CommonPage.restPage(umsResources));
    }
}
