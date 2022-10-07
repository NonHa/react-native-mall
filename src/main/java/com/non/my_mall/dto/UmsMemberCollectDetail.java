package com.non.my_mall.dto;

import lombok.Data;

@Data
public class UmsMemberCollectDetail {
    private Integer collectProductId;
    private String collectProductName;
    private Integer collectProductPromotionPrice;

    private Integer collectSubjectId;
    private String collectSubjectTitle;
    private Integer collectSubjectCollectCount;
    private Integer collectSubjectReadCount;
    private Integer collectSubjectCommentCount;

    private Integer collectTopicId;
    private String collectTopicName;
    private Integer collectTopicReadCount;
    private Integer collectTopicAttentionCount;
    private Integer collectTopicAttendCount;

}
