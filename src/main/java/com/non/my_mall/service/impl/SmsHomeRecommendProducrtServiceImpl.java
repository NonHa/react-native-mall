package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.SmsHomeRecommendProducrtDao;
import com.non.my_mall.dto.SmsHomeRecommendProductParams;
import com.non.my_mall.mbg.model.SmsHomeRecommendProduct;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsHomeRecommendProducrtServiceImpl implements SmsHomeRecommendProducrtDao {
    @Resource
    private SmsHomeRecommendProducrtDao homeRecommendProducrtDao;
    @Override
    public List<SmsHomeRecommendProduct> getList(SmsHomeRecommendProductParams param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        return homeRecommendProducrtDao.getList(param);
    }

    @Override
    public int deleteById(Long id) {
        return homeRecommendProducrtDao.deleteById(id);
    }

    @Override
    public int update(SmsHomeRecommendProduct param) {
        return homeRecommendProducrtDao.update(param);
    }

    @Override
    public int insert(List<SmsHomeRecommendProduct> param) {
        return homeRecommendProducrtDao.insert(param);
    }
}
