package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.OmsOrderReturnReasonDao;
import com.non.my_mall.dto.OmsOrderReturnReasonParam;
import com.non.my_mall.mbg.model.OmsOrderReturnReason;
import com.non.my_mall.service.OmsOrderReturnReasonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {
    @Resource
    private OmsOrderReturnReasonDao orderReturnReasonDao;
    @Override
    public List<OmsOrderReturnReason> getList(OmsOrderReturnReasonParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        return orderReturnReasonDao.getList(param);
    }

    @Override
    public int update(OmsOrderReturnReason param) {
        return orderReturnReasonDao.update(param);
    }

    @Override
    public int addReason(OmsOrderReturnReason param) {
        param.setCreateTime(new Date());
        return orderReturnReasonDao.addReason(param);
    }
}
