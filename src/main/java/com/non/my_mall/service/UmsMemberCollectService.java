package com.non.my_mall.service;

import com.non.my_mall.dto.UmsMemberCollectDetail;
import com.non.my_mall.dto.UmsMemberCollectParams;

import java.util.List;

public interface UmsMemberCollectService {
     List<UmsMemberCollectDetail> getCollectByMemberId(UmsMemberCollectParams params);
}
