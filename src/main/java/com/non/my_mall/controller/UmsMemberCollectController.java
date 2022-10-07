package com.non.my_mall.controller;


import com.non.my_mall.dto.UmsMemberCollectDetail;
import com.non.my_mall.dto.UmsMemberCollectParams;
import com.non.my_mall.service.UmsMemberCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/collect")
@Api(tags = "用户收藏数据")
public class UmsMemberCollectController {

    @Autowired
    private UmsMemberCollectService umsMemberCollectService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "收藏列表")
    public List<UmsMemberCollectDetail> getCollect(@Validated @RequestBody UmsMemberCollectParams params) {
        System.out.println("params"+params);
        List<UmsMemberCollectDetail> collectByMemberId = umsMemberCollectService.getCollectByMemberId(params);
        return collectByMemberId;
    }
}
