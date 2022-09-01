package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.dto.SmsHomeRecommendProductParams;
import com.non.my_mall.dto.SmsHomeRecommendSubjectParam;
import com.non.my_mall.mbg.model.SmsHomeRecommendProduct;
import com.non.my_mall.mbg.model.SmsHomeRecommendSubject;
import com.non.my_mall.service.impl.SmsHomeRecommendProducrtServiceImpl;
import com.non.my_mall.service.impl.SmsHomeRecommendSubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectServiceImpl homeRecommendProducrtDao;
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public CommonPage<SmsHomeRecommendSubject> getList(@RequestBody SmsHomeRecommendSubjectParam homeBrandParam) {
        List<SmsHomeRecommendSubject> list = homeRecommendProducrtDao.getList(homeBrandParam);

        return CommonPage.restPage(list);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam Long id) {
        int count = homeRecommendProducrtDao.deleteById(id);
        if (count > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody SmsHomeRecommendSubject param) {
        int update = homeRecommendProducrtDao.update(param);
        if (update > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody List<SmsHomeRecommendSubject> param) {
        int update = homeRecommendProducrtDao.insert(param);
        if (update > 0) {
            return null;
        } else {
            return "操作失败";
        }
    }
}
