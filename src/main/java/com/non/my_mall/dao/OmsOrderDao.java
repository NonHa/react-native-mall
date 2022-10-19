package com.non.my_mall.dao;

import com.non.my_mall.dto.OmsGenerateOrderParam;
import com.non.my_mall.dto.OmsOrderParams;
import com.non.my_mall.mbg.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OmsOrderDao {
    List<OmsOrder> getList(@Param("queryParam") OmsOrderParams param);
    int delete(Long id);
    int generate(OmsOrder param);

}
