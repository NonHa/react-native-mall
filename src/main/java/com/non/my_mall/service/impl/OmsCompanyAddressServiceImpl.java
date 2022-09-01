package com.non.my_mall.service.impl;

import com.non.my_mall.dao.OmsCompanyAddressDao;
import com.non.my_mall.mbg.model.OmsCompanyAddress;
import com.non.my_mall.service.OmsCompanyAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Resource
    private OmsCompanyAddressDao companyAddressDao;
    @Override
    public List<OmsCompanyAddress> getList() {
        return companyAddressDao.getList();
    }
}
