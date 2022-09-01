package com.non.my_mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.non.my_mall.dto.PmsBrandParams;
import com.non.my_mall.mbg.mapper.PmsBrandMapper;
import com.non.my_mall.mbg.mapper.PmsProductMapper;
import com.non.my_mall.mbg.model.PmsBrand;
import com.non.my_mall.mbg.model.PmsBrandExample;
import com.non.my_mall.mbg.model.PmsProduct;
import com.non.my_mall.mbg.model.PmsProductExample;
import com.non.my_mall.service.PmsBrandService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Resource
    PmsBrandMapper pmsBrandMapper;

    @Resource
    PmsProductMapper productMapper;
    @Override
    public List<PmsBrand> listAllBrand() {
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrandParams brand) {

        PmsBrand pmsBrand = new PmsBrand();
        BeanUtil.copyProperties(brand,pmsBrand);
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0,1));
        }
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

    @Override
    public int updateBrand(Long id, PmsBrandParams brand) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtil.copyProperties(brand,pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setBrandName(pmsBrand.getName());
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andBrandIdEqualTo(id);
        productMapper.updateByExampleSelective(pmsProduct, example);
        return pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @Override
    public int deleteBrand(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(String keyword,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        pmsBrandExample.setOrderByClause("sort desc");
        if(!StringUtils.isEmpty(keyword)) {
            pmsBrandExample.createCriteria().andNameLike("%" + keyword + "%");
        }
        return pmsBrandMapper.selectByExample(pmsBrandExample);
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }
}
