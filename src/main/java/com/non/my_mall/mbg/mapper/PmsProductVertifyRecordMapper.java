package com.non.my_mall.mbg.mapper;

import com.non.my_mall.mbg.model.PmsProductVertifyRecord;
import com.non.my_mall.mbg.model.PmsProductVertifyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductVertifyRecordMapper {
    long countByExample(PmsProductVertifyRecordExample example);

    int deleteByExample(PmsProductVertifyRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductVertifyRecord row);

    int insertSelective(PmsProductVertifyRecord row);

    List<PmsProductVertifyRecord> selectByExample(PmsProductVertifyRecordExample example);

    PmsProductVertifyRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsProductVertifyRecord row, @Param("example") PmsProductVertifyRecordExample example);

    int updateByExample(@Param("row") PmsProductVertifyRecord row, @Param("example") PmsProductVertifyRecordExample example);

    int updateByPrimaryKeySelective(PmsProductVertifyRecord row);

    int updateByPrimaryKey(PmsProductVertifyRecord row);
}