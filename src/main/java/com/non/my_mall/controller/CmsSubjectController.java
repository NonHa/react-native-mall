package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.mbg.model.CmsSubject;
import com.non.my_mall.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品专题管理Controller
 * Created by macro on 2018/6/1.
 */
@Controller
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public List<CmsSubject> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return subjectList;
    }

    @ApiOperation(value = "根据专题名称分页获取商品专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonPage<CmsSubject> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<CmsSubject> subjectList = subjectService.list(keyword, pageNum, pageSize);
        return CommonPage.restPage(subjectList);
    }

    @ApiOperation("新增专题")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody CmsSubject param) {
        int add = subjectService.add(param);
        return add;
    }
}
