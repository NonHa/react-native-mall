package com.non.my_mall.service;

import com.non.my_mall.dto.CmsSubjectCommentDetail;
import com.non.my_mall.dto.CmsSubjectCommentParams;
import com.non.my_mall.dto.SmsHomeRecommendSubjectDetail;
import com.non.my_mall.dto.SmsHomeRecommendSubjectParam;
import com.non.my_mall.mbg.model.CmsSubjectCategory;
import com.non.my_mall.mbg.model.CmsSubjectComment;
import com.non.my_mall.mbg.model.SmsHomeRecommendSubject;
import com.non.my_mall.service.impl.CmsSubjectCategoryServiceImpl;
import com.non.my_mall.service.impl.SmsHomeRecommendSubjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CmsSubjectCategoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsSubjectCategoryTest.class);
    @Autowired
    private CmsSubjectCategoryServiceImpl subjectCategoryService;
    @Autowired
    private SmsHomeRecommendSubjectServiceImpl homeRecommendSubjectService;

    @Autowired
    private CmsSubjectCommentService subjectCommentService;
    @Test
    public void getCategoryList() {
        List<CmsSubjectCategory> list = subjectCategoryService.getList();
        LOGGER.info("list： {}", list);
    }
    @Test
    public void getHomeRecommendSubject() {
        SmsHomeRecommendSubjectParam param = new SmsHomeRecommendSubjectParam();
        param.setRecommendStatus(1);
        param.setCategoryId(2);
        List<SmsHomeRecommendSubject> infoList = homeRecommendSubjectService.getInfoList(param);
        LOGGER.info("list=： {}", infoList);
    }
    @Test
    public void getHomeRecommendSubjectDetail() {

        SmsHomeRecommendSubjectDetail detailById = homeRecommendSubjectService.getDetailById(1L);
        LOGGER.info("list=： {}", detailById);
    }

    @Test
    public void getsubjectCommentService() {
        CmsSubjectCommentParams cmsSubjectCommentParams = new CmsSubjectCommentParams();
        cmsSubjectCommentParams.setId(1L);
        cmsSubjectCommentParams.setPageSize(5);
        cmsSubjectCommentParams.setPage(1);
        List<CmsSubjectCommentDetail> commentById = subjectCommentService.getCommentById(cmsSubjectCommentParams);
        LOGGER.info("list=： {}", commentById);
    }
}
