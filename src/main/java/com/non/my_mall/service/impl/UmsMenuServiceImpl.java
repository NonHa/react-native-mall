package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.UmsMenuDao;
import com.non.my_mall.dto.UmsMenuNode;
import com.non.my_mall.dto.UmsMenuParam;
import com.non.my_mall.mbg.model.UmsMenu;
import com.non.my_mall.service.UmsMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Resource
    private UmsMenuDao menuDao;
    @Override
    public List<UmsMenu> getList(UmsMenuParam params) {
        PageHelper.startPage(params.getPage(),params.getPageSize());
        return menuDao.getList(params);
    }

    @Override
    public int update(UmsMenu params) {
        return menuDao.update(params);
    }

    @Override
    public int add(UmsMenu params) {
        return menuDao.add(params);
    }

    @Override
    public List<UmsMenuNode> getTrees() {
        return menuDao.getTrees();
    }
}
