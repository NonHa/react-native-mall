package com.non.my_mall.service;

import com.non.my_mall.dto.PmsProdcutInfoDetail;
import com.non.my_mall.mbg.model.CmsSubjectCategory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PmsProductTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsSubjectCategoryTest.class);

    @Autowired
    private PmsProductService productService;
    @Test
    public void getProductInfo() {
        PmsProdcutInfoDetail productById = productService.getProductById(1L);
        LOGGER.info("list： {}", productById.getRecommendList());
    }
}
