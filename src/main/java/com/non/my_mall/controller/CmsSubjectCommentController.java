package com.non.my_mall.controller;

import com.non.my_mall.common.api.CommonPage;
import com.non.my_mall.dto.CmsSubjectCommentDetail;
import com.non.my_mall.dto.CmsSubjectCommentParams;
import com.non.my_mall.mbg.model.CmsSubjectComment;
import com.non.my_mall.service.CmsSubjectCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subject/comment")
@Api(tags = "专题评论")
public class CmsSubjectCommentController {
    @Autowired
    private CmsSubjectCommentService subjectCommentService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "评论列表")
    public CommonPage<CmsSubjectCommentDetail> getList(@RequestBody CmsSubjectCommentParams params) {

        List<CmsSubjectCommentDetail> commentById = subjectCommentService.getCommentById(params);
        return CommonPage.restPage(commentById);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增评论")
    public int create( @RequestBody CmsSubjectComment params) {
        int comment = subjectCommentService.createComment(params);
        return comment;
    }
}
