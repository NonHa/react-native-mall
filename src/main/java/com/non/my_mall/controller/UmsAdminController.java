package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.common.api.CommonResult;
import com.non.my_mall.dto.UmsAdminLoginParam;
import com.non.my_mall.dto.UmsAdminParam;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.mbg.model.UmsPermission;
import com.non.my_mall.service.UmsAdminService;
import com.non.my_mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Api(tags = "UmsAdminController", description = "後臺用戶管理")
@RequestMapping("admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsRoleService roleService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
    @ApiOperation(value = "用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult info(Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        UmsAdmin adminByUsername = adminService.getAdminByUsername(principal.getName());
        HashMap<String, Object> data = new HashMap<>();
        data.put("username", adminByUsername.getUsername());
        System.out.println("adminByUsername.getId()"+adminByUsername.getId());
        data.put("menus", roleService.getMenuList(adminByUsername.getId()));
        data.put("role", adminService.getRoleList(adminByUsername.getId()));
        return CommonResult.success(data);
    }
    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = null;
        return CommonResult.success(permissionList);
    }

    @ApiOperation("获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> getList(@RequestBody UmsAdminParam param) {
        List<UmsAdmin> list = adminService.list(param);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("更新用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getList(@RequestBody UmsAdmin param) {
        int update = adminService.update(param.getId(), param);
        if (update > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }

    }

    @ApiOperation("更新用户 角色信息")
    @RequestMapping(value = "/update/role", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateRole(@RequestParam(value = "adminId") Long adminId,@RequestParam(value = "roleIds") String roleIds) {
        int update = adminService.updateRole(adminId,  Arrays.asList(roleIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList()));
        if (update > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }

    }
}
