package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.SmsHomeRecommendSubjectDao;
import com.non.my_mall.dto.SmsHomeRecommendSubjectParam;
import com.non.my_mall.mbg.model.SmsHomeRecommendSubject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectDao {
    @Resource
    private SmsHomeRecommendSubjectDao homeRecommendSubjectDao;
    @Override
    public List<SmsHomeRecommendSubject> getList(SmsHomeRecommendSubjectParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        return homeRecommendSubjectDao.getList(param);
    }

    @Override
    public int deleteById(Long id) {
        return homeRecommendSubjectDao.deleteById(id);
    }

    @Override
    public int update(SmsHomeRecommendSubject param) {
        return homeRecommendSubjectDao.update(param);
    }

    @Override
    public int insert(List<SmsHomeRecommendSubject> param) {
        return homeRecommendSubjectDao.insert(param);
    }
}
