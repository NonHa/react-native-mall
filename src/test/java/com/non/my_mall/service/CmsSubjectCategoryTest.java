package com.non.my_mall.service;

import com.non.my_mall.mbg.model.CmsSubjectCategory;
import com.non.my_mall.service.impl.CmsSubjectCategoryServiceImpl;
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

    @Test
    public void getCategoryList() {
        List<CmsSubjectCategory> list = subjectCategoryService.getList();
        LOGGER.info("list： {}", list);
    }
}
