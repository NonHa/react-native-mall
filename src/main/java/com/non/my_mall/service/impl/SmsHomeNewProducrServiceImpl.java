package com.non.my_mall.service.impl;

import com.non.my_mall.dao.SmsHomeNewProducrDao;
import com.non.my_mall.dto.SmsHomeBrandParam;
import com.non.my_mall.dto.SmsHomeNewProductParam;
import com.non.my_mall.mbg.model.SmsHomeBrand;
import com.non.my_mall.mbg.model.SmsHomeNewProduct;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsHomeNewProducrServiceImpl implements SmsHomeNewProducrDao {
    @Resource
    private SmsHomeNewProducrDao homeNewProducrDao;

    @Override
    public List<SmsHomeNewProduct> getList(SmsHomeNewProductParam param) {
        return homeNewProducrDao.getList(param);
    }

    @Override
    public int deleteById(Long id) {
        return homeNewProducrDao.deleteById(id);
    }

    @Override
    public int update(SmsHomeNewProduct param) {
        return homeNewProducrDao.update(param);
    }

    @Override
    public int insert(List<SmsHomeNewProduct> param) {
        return homeNewProducrDao.insert(param);
    }
}
