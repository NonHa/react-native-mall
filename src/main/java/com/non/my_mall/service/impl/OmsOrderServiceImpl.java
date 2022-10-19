package com.non.my_mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.non.my_mall.dao.OmsOrderDao;
import com.non.my_mall.dao.OmsOrderItemDao;
import com.non.my_mall.domain.CartPromotionItem;
import com.non.my_mall.dto.OmsGenerateOrderParam;
import com.non.my_mall.dto.OmsOrderParams;
import com.non.my_mall.mbg.model.OmsOrder;
import com.non.my_mall.mbg.model.OmsOrderItem;
import com.non.my_mall.mbg.model.UmsAdmin;
import com.non.my_mall.service.OmsCarItemService;
import com.non.my_mall.service.OmsOrderService;
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
    @Override
    public List<OmsOrder> getList(OmsOrderParams param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        return orderDao.getList(param);
    }

    @Override
    public int delete(Long id) {
        return orderDao.delete(id);
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
            orderItemList.add(orderItem);
        }
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setMemberId(currentMember.getId());
        omsOrder.setReceiverName(currentMember.getUsername());
        omsOrder.setReceiverPhone(currentMember.getUsername());
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
