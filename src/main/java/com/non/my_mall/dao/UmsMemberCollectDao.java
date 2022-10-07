package com.non.my_mall.dao;

import com.non.my_mall.dto.UmsMemberCollectDetail;
import com.non.my_mall.dto.UmsMemberCollectParams;

import java.util.List;

public interface UmsMemberCollectDao {
    List<UmsMemberCollectDetail> getCollectProductByMemberId(UmsMemberCollectParams params);
    List<UmsMemberCollectDetail> getCollectSubjectByMemberId(UmsMemberCollectParams params);
    List<UmsMemberCollectDetail> getCollectTopicByMemberId(UmsMemberCollectParams params);

}
