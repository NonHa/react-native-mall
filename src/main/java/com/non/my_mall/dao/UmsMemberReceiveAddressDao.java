package com.non.my_mall.dao;

import com.non.my_mall.mbg.model.UmsMemberReceiveAddress;

import java.util.List;

public interface UmsMemberReceiveAddressDao {
    List<UmsMemberReceiveAddress> getCurrentMemberAddressList(Long memberId, Long id);
    int addAddress(UmsMemberReceiveAddress param);
    int updateAddressById(UmsMemberReceiveAddress param);
}
