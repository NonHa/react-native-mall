package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.UmsResourceDao;
import com.non.my_mall.dto.UmsResourceParam;
import com.non.my_mall.mbg.model.UmsResource;
import com.non.my_mall.service.UmsResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Resource
    private UmsResourceDao resourceDao;

    @Override
    public List<UmsResource> getList(UmsResourceParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        return resourceDao.getList(param);
    }
    @Override
    public int updateById(UmsResource param) {
        return resourceDao.updateById(param);
    }

    @Override
    public int addCate(UmsResource param) {
        UmsResource umsResource = new UmsResource();
        BeanUtil.copyProperties(param,umsResource);
        umsResource.setCreateTime(new Date());
        return resourceDao.addCate(umsResource);
    }
}
