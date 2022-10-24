package com.non.my_mall.service;

import com.non.my_mall.mbg.model.UmsMemberReceiveAddress;

import java.util.List;

public interface UmsMemberReceiveAddressService {
    List<UmsMemberReceiveAddress> getCurrentMemberAddressList();
    int addAddress(UmsMemberReceiveAddress param);
    UmsMemberReceiveAddress getItem(Long id);
}
