package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.dao.CmsSubjectCategoryDao;
import com.non.my_mall.mbg.model.CmsSubjectCategory;
import com.non.my_mall.service.impl.CmsSubjectCategoryServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/home/subject/category")
@Api(tags = "专题分类")
public class CmsSubjectCategoryController {
    @Autowired
    private CmsSubjectCategoryServiceImpl subjectCategoryService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonPage<CmsSubjectCategory> getList() {
        List<CmsSubjectCategory> list = subjectCategoryService.getList();
        return CommonPage.restPage(list);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody CmsSubjectCategory param) {
        int i = subjectCategoryService.addCategory(param);
        return i;
    }
}
