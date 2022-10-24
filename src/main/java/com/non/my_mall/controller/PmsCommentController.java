package com.non.my_mall.controller;

import com.non.my_mall.mbg.model.PmsComment;
import com.non.my_mall.service.PmsCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/product/comment")
@Api(tags = "商品评论")
public class PmsCommentController {
    @Resource
    private PmsCommentService commentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新增评论")
    public Integer addComment(@RequestBody PmsComment param) {
        return commentService.addComment(param);
    }
}
