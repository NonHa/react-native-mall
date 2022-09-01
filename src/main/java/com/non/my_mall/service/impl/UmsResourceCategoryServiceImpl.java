package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.UmsResourceCategoryDao;
import com.non.my_mall.dto.UmsResourceCategoryParam;
import com.non.my_mall.dto.UmsResourceCategoryWithChildren;
import com.non.my_mall.mbg.model.UmsResourceCategory;
import com.non.my_mall.service.UmsResourceCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Resource
    private UmsResourceCategoryDao resourceCategoryDao;
    @Override
    public List<UmsResourceCategory> getListAll(UmsResourceCategoryParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        return resourceCategoryDao.getListAll(param);
    }

    @Override
    public List<UmsResourceCategoryWithChildren> getTree() {
        return resourceCategoryDao.getTree();
    }
}
