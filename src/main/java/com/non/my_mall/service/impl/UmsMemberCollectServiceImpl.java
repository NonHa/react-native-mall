package com.non.my_mall.service.impl;

import com.non.my_mall.dao.UmsMemberCollectDao;
import com.non.my_mall.dto.UmsMemberCollectDetail;
import com.non.my_mall.dto.UmsMemberCollectParams;
import com.non.my_mall.service.UmsMemberCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UmsMemberCollectServiceImpl implements UmsMemberCollectService {
    @Resource
    private UmsMemberCollectDao memberCollectDao;

    @Override
    public List<UmsMemberCollectDetail> getCollectByMemberId(UmsMemberCollectParams params) {
        Long collectType = params.getCollectType();
        if (collectType == 1) {
            return memberCollectDao.getCollectProductByMemberId(params);
        } else if (collectType == 2) {
            return memberCollectDao.getCollectSubjectByMemberId(params);
        } else if (collectType == 3) {
            return memberCollectDao.getCollectTopicByMemberId(params);
        }
       return null;
    }
}
