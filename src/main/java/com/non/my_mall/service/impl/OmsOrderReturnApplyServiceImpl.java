package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.non.my_mall.dao.OmsOrderReturnApplyDao;
import com.non.my_mall.dto.OmsOrderReturnApplyParam;
import com.non.my_mall.dto.OmsUpdateParam;
import com.non.my_mall.mbg.model.OmsOrderReturnApply;
import com.non.my_mall.service.OmsOrderReturnApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {

    @Resource
    private OmsOrderReturnApplyDao orderReturnApplyDao;
    @Override
    public List<OmsOrderReturnApply> getList(OmsOrderReturnApplyParam param) {
        return orderReturnApplyDao.getList(param);
    }
    @Override
    public int update(OmsUpdateParam param) {
        OmsUpdateParam omsUpdateParam = new OmsUpdateParam();
        BeanUtil.copyProperties(param,omsUpdateParam);
        Integer status = param.getStatus();
        if (status.equals(1) || status.equals(3)) {
            omsUpdateParam.setHandleTime(new Date());
        } else if (status.equals(2)) {
            omsUpdateParam.setReceiveTime(new Date());
        }

        return orderReturnApplyDao.update(omsUpdateParam);
    }
}
