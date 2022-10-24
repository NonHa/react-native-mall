package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.OmsOrderDao;
import com.non.my_mall.dao.OmsOrderItemDao;
import com.non.my_mall.domain.CartPromotionItem;
import com.non.my_mall.dto.*;
import com.non.my_mall.mbg.model.OmsOrder;
import com.non.my_mall.mbg.model.OmsOrderItem;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.mbg.model.UmsMemberReceiveAddress;
import com.non.my_mall.service.OmsCarItemService;
import com.non.my_mall.service.OmsOrderService;
import com.non.my_mall.service.UmsMemberReceiveAddressService;
import com.non.my_mall.service.UmsMemeberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OmsOrderServiceImpl implements OmsOrderService {
    @Resource
    private OmsOrderDao orderDao;

    @Value("${redis.key.orderId}")
    private String REDIS_KEY_ORDER_ID;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Resource
    private RedisServiceImpl redisService;

    @Resource
    private UmsMemeberService memeberService;

    @Resource
    private OmsCarItemService carItemService;

    @Resource
    private OmsOrderItemDao omsOrderItemDao;

    @Resource
    private UmsMemberReceiveAddressService memberReceiveAddressService;
    @Override
    public List<OmsOrder> getList(OmsOrderParams param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        return orderDao.getList(param);
    }

    @Override
    public List<OmsOrderDetail> getMember(OmsOrderParams param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        UmsAdmin currentMember = memeberService.getCurrentMember();
        param.setMemberId(currentMember.getId());
        return orderDao.getMember(param);
    }

    @Override
    public int delete(Long id) {
        return orderDao.delete(id);
    }

    @Override
    public ConfirmOrderResult generateConfirmOrder(List<Long> carIds) {
        ConfirmOrderResult result = new ConfirmOrderResult();
        UmsAdmin currentMember = memeberService.getCurrentMember();
        List<CartPromotionItem> promotionItems = carItemService.listPromotion(currentMember.getId(), carIds);
        result.setCartPromotionItemList(promotionItems);
        List<UmsMemberReceiveAddress> currentMemberAddressList = memberReceiveAddressService.getCurrentMemberAddressList();
        result.setMemberReceiveAddressList(currentMemberAddressList);
        return result;
    }

    @Override
    public Map<String, Object> generate(OmsGenerateOrderParam param) {
        List<OmsOrderItem> orderItemList = new ArrayList<>();

        UmsAdmin currentMember = memeberService.getCurrentMember();
        List<CartPromotionItem> promotionItems = carItemService.listPromotion(currentMember.getId(), param.getCartIds());
        for (CartPromotionItem promotionItem: promotionItems) {
            OmsOrderItem orderItem = new OmsOrderItem();
            orderItem.setProductId(promotionItem.getProductId());
            orderItem.setProductName(promotionItem.getProductName());
            orderItem.setProductPic(promotionItem.getProductPic());
            orderItem.setProductAttr(promotionItem.getProductAttr());
            orderItem.setProductBrand(promotionItem.getProductBrand());
            orderItem.setProductSn(promotionItem.getProductSn());
            orderItem.setProductPrice(promotionItem.getPrice());
            orderItem.setProductQuantity(promotionItem.getQuantity());
            orderItem.setProductSkuId(promotionItem.getProductSkuId());
            orderItem.setProductSkuCode(promotionItem.getProductSkuCode());
            orderItem.setProductCategoryId(promotionItem.getProductCategoryId());
            orderItem.setPromotionAmount(promotionItem.getReduceAmount());
            orderItem.setPromotionName(promotionItem.getPromotionMessage());
            orderItem.setGiftIntegration(promotionItem.getIntegration());
            orderItem.setGiftGrowth(promotionItem.getGrowth());
            orderItem.setStatus(0);
            orderItemList.add(orderItem);
        }
        UmsMemberReceiveAddress item = memberReceiveAddressService.getItem(param.getMemberReceiveAddressId());

        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setReceiverCity(item.getCity());
        omsOrder.setReceiverName(item.getName());
        omsOrder.setReceiverPhone(item.getPhoneNumber());
        omsOrder.setReceiverDetailAddress(item.getDetailAddress());
        omsOrder.setReceiverProvince(item.getProvince());
        omsOrder.setReceiverRegion(item.getRegion());
        omsOrder.setMemberId(currentMember.getId());

        omsOrder.setCreateTime(new Date());
        omsOrder.setSourceType(1);
        omsOrder.setPayType(0);
        omsOrder.setStatus(0);
        omsOrder.setOrderSn(generateOrderSn(omsOrder));


        orderDao.generate(omsOrder);
        for (OmsOrderItem orderItem : orderItemList) {
            orderItem.setOrderId(omsOrder.getId());
            orderItem.setOrderSn(omsOrder.getOrderSn());
        }
        omsOrderItemDao.insertList(orderItemList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("order", omsOrder);
        return map;
    }

    @Override
    public Integer paySuccess(OrderPayParam param) {
        Integer count = 0;
        for (Long orderItemId : param.getOrderItemIds()) {
            OmsOrderItem omsOrderItem = new OmsOrderItem();
            omsOrderItem.setStatus(1);
            omsOrderItem.setPaymentTime(new Date());
            omsOrderItem.setId(orderItemId);
            count += omsOrderItemDao.update(omsOrderItem);
        }

        return count;
    }

    // 确认订单
    @Override
    public Integer confirmOrder(List<Long> ids) {
        for (Long id : ids) {
            OmsOrderItem omsOrderItem = new OmsOrderItem();
            omsOrderItem.setId(id);
            omsOrderItem.setStatus(3);
            omsOrderItemDao.update(omsOrderItem);
        }
        return null;
    }

    /**
     * 生成18位订单编号:8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    private String generateOrderSn(OmsOrder order) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = REDIS_DATABASE+":"+ REDIS_KEY_ORDER_ID + date;
        Long increment = redisService.incr(key, 1);
        sb.append(date);
        sb.append(String.format("%02d", order.getSourceType()));
        sb.append(String.format("%02d", order.getPayType()));
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }
}
