package com.non.my_mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.non.my_mall.dao.OmsCarItemDao;
import com.non.my_mall.domain.CartPromotionItem;
import com.non.my_mall.dto.SecurityUser;
import com.non.my_mall.mbg.mapper.OmsCartItemMapper;
import com.non.my_mall.mbg.model.OmsCartItem;
import com.non.my_mall.mbg.model.OmsCartItemExample;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.service.OmsCarItemService;
import com.non.my_mall.service.OmsPromotionService;
import com.non.my_mall.service.UmsMemeberService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OmsCarItemServiceImpl implements OmsCarItemService {
    @Resource
    private OmsCarItemDao carItemDao;
    @Resource
    private OmsCartItemMapper cartItemMapper;

    @Resource
    private UmsMemeberService memeberService;

    @Resource
    private OmsPromotionService promotionService;
    @Override
    public List<OmsCartItem> getList() {
        UmsAdmin currentMember = memeberService.getCurrentMember();
        return carItemDao.getList(currentMember.getId());
    }

    @Override
    public int add(OmsCartItem params) {
        UmsAdmin currentMember = memeberService.getCurrentMember();
        params.setMemberId(currentMember.getId());
        OmsCartItem carItem = this.getCarItem(params);
        params.setDeleteStatus(0);
        params.setMemberNickname(currentMember.getNickName());
        if (carItem == null) {
            params.setCreateDate(new Date());
            return carItemDao.add(params);
        } else {
            carItem.setModifyDate(new Date());
            carItem.setProductAttr(params.getProductAttr());
            carItem.setQuantity(carItem.getQuantity()+params.getQuantity());
            return carItemDao.update(carItem);
        }


    }

    /**
     * 根据会员id,商品id和规格获取购物车中商品
     */
    private OmsCartItem getCarItem(OmsCartItem carItem) {
        OmsCartItemExample omsCartItemExample = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = omsCartItemExample.createCriteria();
        criteria.andMemberIdEqualTo(carItem.getMemberId()).andProductIdEqualTo(carItem.getProductId()).andDeleteStatusEqualTo(0);
        if (!StringUtils.isEmpty(carItem.getProductSkuId())) {
            criteria.andProductSkuIdEqualTo(carItem.getProductSkuId());
        }
        List<OmsCartItem> omsCartItems = cartItemMapper.selectByExample(omsCartItemExample);
        if (omsCartItems.size() > 0) {
            return omsCartItems.get(0);
        }
        return null;
    }

    @Override
    public List<CartPromotionItem> listPromotion(Long memberId, List<Long> carIds) {

        List<OmsCartItem> list = getList();
//        if (CollUtil.isNotEmpty(carIds)) {
            list = list.stream().filter(item -> carIds.contains(item.getId())).collect(Collectors.toList());
//        }

         List<CartPromotionItem> promotionItems = new ArrayList<>();
        if (!CollectionUtil.isEmpty(list)) {
            promotionItems = promotionService.calcCartPromotion(list);
        }
        return promotionItems;
    }
}
