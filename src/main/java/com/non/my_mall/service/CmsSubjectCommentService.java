package com.non.my_mall.service;

import com.non.my_mall.dto.CmsSubjectCommentDetail;
import com.non.my_mall.dto.CmsSubjectCommentParams;
import com.non.my_mall.mbg.model.CmsSubjectComment;

import java.util.List;

public interface CmsSubjectCommentService {
    List<CmsSubjectCommentDetail> getCommentById(CmsSubjectCommentParams params);
    int createComment(CmsSubjectComment params);
}
