package com.non.my_mall.service;

import com.non.my_mall.dto.PmsBrandParams;
import com.non.my_mall.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrandParams brand);

    int updateBrand(Long id, PmsBrandParams brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(String keyword,int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
