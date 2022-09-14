package com.non.my_mall.dto;

import lombok.Data;

@Data
public class SmsHomeRecommendSubjectParam extends PageParams{
    private String subjectName;
    private Integer recommendStatus;
    private Integer categoryId;

}
