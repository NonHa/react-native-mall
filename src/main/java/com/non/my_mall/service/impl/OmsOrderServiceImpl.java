package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.OmsOrderDao;
import com.non.my_mall.dto.OmsOrderParams;
import com.non.my_mall.mbg.model.OmsOrder;
import com.non.my_mall.service.OmsOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OmsOrderServiceImpl implements OmsOrderService {
    @Resource
    private OmsOrderDao orderDao;
    @Override
    public List<OmsOrder> getList(OmsOrderParams param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        return orderDao.getList(param);
    }

    @Override
    public int delete(Long id) {
        return orderDao.delete(id);
    }
}
