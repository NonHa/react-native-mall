package com.non.my_mall.service.impl;

import com.non.my_mall.dao.CmsSubjectCategoryDao;
import com.non.my_mall.mbg.model.CmsSubjectCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsSubjectCategoryServiceImpl implements CmsSubjectCategoryDao {
    @Autowired
    private CmsSubjectCategoryDao subjectCategoryDao;
    @Override
    public List<CmsSubjectCategory> getList() {
        return subjectCategoryDao.getList();
    }

    @Override
    public int addCategory(CmsSubjectCategory param) {
        return subjectCategoryDao.addCategory(param);
    }
}
