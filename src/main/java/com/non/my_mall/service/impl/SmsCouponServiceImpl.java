package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.SmsCouponDao;
import com.non.my_mall.dao.SmsCouponProductCategoryRelationDao;
import com.non.my_mall.dao.SmsCouponProductRelationDao;
import com.non.my_mall.dto.SmsCouponParam;
import com.non.my_mall.dto.SmsCouponRalationParam;
import com.non.my_mall.mbg.model.SmsCoupon;
import com.non.my_mall.mbg.model.SmsCouponProductCategoryRelation;
import com.non.my_mall.mbg.model.SmsCouponProductRelation;
import com.non.my_mall.service.SmsCouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SmsCouponServiceImpl implements SmsCouponService {
    @Resource
    private SmsCouponDao couponDao;

    @Resource
    private SmsCouponProductRelationDao couponProductRelationDao;

    @Resource
    private SmsCouponProductCategoryRelationDao couponProductCategoryRelationDao;
    @Override
    public List<SmsCoupon> getList(SmsCouponParam param) {

        PageHelper.startPage(param.getPage(),param.getPageSize());
        return couponDao.getList(param);
    }

    @Override
    public int delete(Long id) {
        return couponDao.delete(id);
    }

    @Override
    public int update(SmsCouponRalationParam param) {

        int count = couponDao.update(param);
        updateCoupProductAndCategory(param);
        return count;
    }

    @Override
    public int insert(SmsCouponRalationParam param) {

        param.setCount(param.getPublishCount());
        param.setUseCount(0);
        param.setReceiveCount(0);
        int count = couponDao.insert(param);
        updateCoupProductAndCategory(param);
        return count;
    }

    public void updateCoupProductAndCategory(SmsCouponRalationParam param) {
        if (param.getUseType().equals(1)) {
            couponProductCategoryRelationDao.delete(param.getId());
            for (SmsCouponProductCategoryRelation item: param.getProductCategoryRelationList()) {
                item.setCouponId(param.getId());
            }
            couponProductCategoryRelationDao.insert(param.getProductCategoryRelationList());
        }
        if (param.getUseType().equals(2)) {
            couponProductRelationDao.delete(param.getId());
            for (SmsCouponProductRelation item: param.getProductRelationList()) {
                item.setCouponId(param.getId());
            }
            couponProductRelationDao.insert(param.getProductRelationList());
        }
    }
}
