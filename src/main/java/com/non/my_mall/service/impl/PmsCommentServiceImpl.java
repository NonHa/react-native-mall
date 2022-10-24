package com.non.my_mall.service.impl;

import com.non.my_mall.dao.PmsCommentDao;
import com.non.my_mall.mbg.model.PmsComment;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.mbg.model.UmsMember;
import com.non.my_mall.service.PmsCommentService;
import com.non.my_mall.service.UmsMemeberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class PmsCommentServiceImpl implements PmsCommentService {
    @Resource
    private PmsCommentDao commentDao;
    @Resource
    private UmsMemeberService memeberService;
    @Override
    public Integer addComment(PmsComment param) {

        UmsAdmin currentMember = memeberService.getCurrentMember();
        param.setCreateTime(new Date());
        param.setMemberNickName(currentMember.getNickName());
        return commentDao.addComment(param);
    }
}
