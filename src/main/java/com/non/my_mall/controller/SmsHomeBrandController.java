package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.dao.SmsHomeBrandDao;
import com.non.my_mall.dto.SmsHomeBrandParam;
import com.non.my_mall.mbg.model.SmsHomeBrand;
import com.non.my_mall.service.SmsHomeBrandService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "品牌推荐")
@RequestMapping(value = "/home/brand")
public class SmsHomeBrandController {
    @Autowired
    private SmsHomeBrandService homeBrandService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public CommonPage<SmsHomeBrand> getList(@RequestBody SmsHomeBrandParam homeBrandParam) {
        List<SmsHomeBrand> list = homeBrandService.getList(homeBrandParam);
        return CommonPage.restPage(list);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String getList(@RequestParam Long id) {
        int count = homeBrandService.deleteById(id);
        if (count > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String getList(@RequestBody SmsHomeBrand param) {
        int update = homeBrandService.update(param);
        if (update > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String getList(@RequestBody List<SmsHomeBrand> param) {
        int update = homeBrandService.insert(param);
        if (update > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }
}
