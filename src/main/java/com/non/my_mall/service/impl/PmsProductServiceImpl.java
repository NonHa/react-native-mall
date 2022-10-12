package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.PmsProductDao;
import com.non.my_mall.dto.PmsProdcutInfoDetail;
import com.non.my_mall.dto.PmsProductQueryParam;
import com.non.my_mall.mbg.mapper.PmsProductMapper;
import com.non.my_mall.mbg.model.PmsComment;
import com.non.my_mall.mbg.model.PmsProduct;
import com.non.my_mall.mbg.model.PmsProductExample;
import com.non.my_mall.service.PmsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);

    @Resource
    private PmsProductMapper productMapper;

    @Resource
    private PmsProductDao productDao;
    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if (productQueryParam.getPublishStatus() != null) {
            criteria.andPublishStatusEqualTo(productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus() != null) {
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            criteria.andNameLike("%" + productQueryParam.getKeyword() + "%");
        }
        if (!StringUtils.isEmpty(productQueryParam.getProductSn())) {
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        }
        if (productQueryParam.getBrandId() != null) {
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());
        }

        return productMapper.selectByExample(productExample);
    }

    public List<PmsProduct> list(String keyword) {
        return productDao.getSimpleList(keyword);
    }
    @Override
    public int updateDeleteStatus(List ids, Integer deleteStatus) {
        PmsProduct record = new PmsProduct();
        record.setDeleteStatus(deleteStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public PmsProdcutInfoDetail getProductById(Long id) {
        PmsProdcutInfoDetail productById = productDao.getProductById(id);
        List<PmsComment> commentById = productDao.getCommentById(id);
        productById.setCommentCount(commentById.size());
        productById.setComment(commentById.get(0));
        return productById;
    }

    @Override
    public List<PmsComment> getCommentById(Long id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        return productDao.getCommentById(id);
    }
}
