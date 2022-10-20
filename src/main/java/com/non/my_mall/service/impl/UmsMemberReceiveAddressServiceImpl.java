package com.non.my_mall.service.impl;

import com.non.my_mall.dao.UmsMemberReceiveAddressDao;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.mbg.model.UmsMemberReceiveAddress;
import com.non.my_mall.service.UmsMemberReceiveAddressService;
import com.non.my_mall.service.UmsMemeberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {
    @Resource
    private UmsMemberReceiveAddressDao memberReceiveAddressDao;
    @Resource
    private UmsMemeberService memeberService;
    @Override
    public List<UmsMemberReceiveAddress> getCurrentMemberAddressList() {
        UmsAdmin currentMember = memeberService.getCurrentMember();
        return memberReceiveAddressDao.getCurrentMemberAddressList(currentMember.getId());
    }

    @Override
    public int addAddress(UmsMemberReceiveAddress param) {
        UmsAdmin currentMember = memeberService.getCurrentMember();
        param.setMemberId(currentMember.getId());
        Integer defaultStatus = param.getDefaultStatus();
        if (defaultStatus == 1) {
            List<UmsMemberReceiveAddress> currentMemberAddressList = getCurrentMemberAddressList();
            for (UmsMemberReceiveAddress item: currentMemberAddressList) {
                UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
                if (item.getDefaultStatus() == 1) {
                    umsMemberReceiveAddress.setId(item.getId());
                    umsMemberReceiveAddress.setDefaultStatus(0);
                    memberReceiveAddressDao.updateAddressById(umsMemberReceiveAddress);
                }
            }

        }
        return memberReceiveAddressDao.addAddress(param);
    }
}
