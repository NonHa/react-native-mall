package com.non.my_mall.controller;

import com.non.my_mall.mbg.model.OmsCartItem;
import com.non.my_mall.service.OmsCarItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "购物车")
@RequestMapping("/shopping/car")
public class OmsCarItemController {
    @Autowired
    private OmsCarItemService carItemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增")
    @ResponseBody
    public int addItem(@RequestBody OmsCartItem params) {
        return carItemService.add(params);
    }
}
