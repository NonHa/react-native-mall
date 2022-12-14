package com.non.my_mall.service;

import com.non.my_mall.dao.UmsMemberDao;
import com.non.my_mall.dto.UmsMemberCollectDetail;
import com.non.my_mall.dto.UmsMemberCollectParams;
import com.non.my_mall.mbg.model.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest

public class UmsMemberTest {
    @Resource
    private UmsMemeberService memberDao;

    @Resource
    private UmsMemberCollectService memberCollectService;
    @Test
    public void insertMember() {
        UmsMember umsMember = new UmsMember();
        umsMember.setPassword("12345");
        umsMember.setPhone("13123233458");
        UmsMember umsMember1 = memberDao.addMemeber(umsMember);
        System.out.println("umsMember1===>"+umsMember1);
    }
    @Test
    public void getMember() {
        List<UmsMember> member = memberDao.getMember("13123233458");
        System.out.println("umsMember1===>"+member);
    }

    @Test
    public void getMemberCollect() {
        UmsMemberCollectParams umsMemberCollectParams = new UmsMemberCollectParams();
        umsMemberCollectParams.setMemberId(11L);
        umsMemberCollectParams.setCollectType(1L);
        List<UmsMemberCollectDetail> collectByMemberId = memberCollectService.getCollectByMemberId(umsMemberCollectParams);
        System.out.println("umsMember1===>"+collectByMemberId);
    }
}
