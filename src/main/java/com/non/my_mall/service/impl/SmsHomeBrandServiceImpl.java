package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.SmsHomeBrandDao;
import com.non.my_mall.dto.SmsHomeBrandParam;
import com.non.my_mall.mbg.model.SmsHomeBrand;
import com.non.my_mall.service.SmsHomeBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
    @Resource
    private SmsHomeBrandDao homeBrandDao;
    @Override
    public List<SmsHomeBrand> getList(SmsHomeBrandParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        return homeBrandDao.getList(param);
    }

    @Override
    public int deleteById(Long id) {
        return homeBrandDao.deleteById(id);
    }

    @Override
    public int update(SmsHomeBrand param) {
        return homeBrandDao.update(param);
    }

    @Override
    public int insert(List<SmsHomeBrand> param) {
        return homeBrandDao.insert(param);
    }
}
