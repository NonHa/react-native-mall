package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.dao.SmsHomeNewProducrDao;
import com.non.my_mall.dto.SmsHomeBrandParam;
import com.non.my_mall.dto.SmsHomeNewProductParam;
import com.non.my_mall.mbg.model.SmsHomeBrand;
import com.non.my_mall.mbg.model.SmsHomeNewProduct;
import com.non.my_mall.service.impl.SmsHomeNewProducrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home/newproduct")
public class SmsHomeNewProducrController {
    @Autowired
    private SmsHomeNewProducrServiceImpl homeNewProducrDao;
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public CommonPage<SmsHomeNewProduct> getList(@RequestBody SmsHomeNewProductParam homeBrandParam) {
        List<SmsHomeNewProduct> list = homeNewProducrDao.getList(homeBrandParam);
        return CommonPage.restPage(list);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String getList(@RequestParam Long id) {
        int count = homeNewProducrDao.deleteById(id);
        if (count > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String getList(@RequestBody SmsHomeNewProduct param) {
        int update = homeNewProducrDao.update(param);
        if (update > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String getList(@RequestBody List<SmsHomeNewProduct> param) {
        int update = homeNewProducrDao.insert(param);
        if (update > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }
}
