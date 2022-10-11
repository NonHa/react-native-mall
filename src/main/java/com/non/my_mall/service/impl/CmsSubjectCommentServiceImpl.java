package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.CmsSubjectCommentDao;
import com.non.my_mall.dto.CmsSubjectCommentDetail;
import com.non.my_mall.dto.CmsSubjectCommentParams;
import com.non.my_mall.mbg.model.CmsSubjectComment;
import com.non.my_mall.service.CmsSubjectCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CmsSubjectCommentServiceImpl implements CmsSubjectCommentService {
    @Resource
    private CmsSubjectCommentDao subjectCommentDao;
    @Override
    public List<CmsSubjectCommentDetail> getCommentById(CmsSubjectCommentParams params) {
        PageHelper.startPage(params.getPage(),params.getPageSize());
        return subjectCommentDao.getCommentById(params);
    }

    @Override
    public int createComment(CmsSubjectComment params) {
        params.setCreateTime(new Date());
        return subjectCommentDao.createComment(params);
    }
}
