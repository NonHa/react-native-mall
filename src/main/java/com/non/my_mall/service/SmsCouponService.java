package com.non.my_mall.service;

import com.non.my_mall.dto.SmsCouponParam;
import com.non.my_mall.dto.SmsCouponRalationParam;
import com.non.my_mall.mbg.model.SmsCoupon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SmsCouponService {
    @Transactional
    List<SmsCoupon> getList(SmsCouponParam param);
    @Transactional
    int delete(Long id);
    @Transactional
    int update(SmsCouponRalationParam param);

    @Transactional
    int insert(SmsCouponRalationParam param);
}
