package com.non.my_mall.dto;

import com.non.my_mall.mbg.model.CmsSubjectComment;
import lombok.Data;

@Data
public class CmsSubjectCommentDetail extends CmsSubjectComment {
    private String memberCity;
}
