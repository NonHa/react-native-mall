package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.SmsHomeAdvertiseDao;
import com.non.my_mall.dto.SmsHomeAdvertiseParams;
import com.non.my_mall.mbg.model.SmsHomeAdvertise;
import com.non.my_mall.service.SmsHomeAdvertiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {
    @Resource
    private SmsHomeAdvertiseDao homeAdvertiseDao;
    @Override
    public List<SmsHomeAdvertise> getList(SmsHomeAdvertiseParams params) {
        System.out.println("params==>"+params.getName());
        PageHelper.startPage(params.getPage(), params.getPageSize());
        return homeAdvertiseDao.getList(params);
    }

    @Override
    public int deleteById(Long id) {
        return homeAdvertiseDao.deleteById(id);
    }

    @Override
    public int updateById(SmsHomeAdvertise param) {
        return homeAdvertiseDao.updateById(param);
    }

    @Override
    public int addItem(SmsHomeAdvertise param) {
        return homeAdvertiseDao.addItem(param);
    }
}
