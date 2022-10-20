package com.non.my_mall.service.impl;

import com.non.my_mall.dao.PmsProductDao;
import com.non.my_mall.domain.CartPromotionItem;
import com.non.my_mall.dto.PromotionProduct;
import com.non.my_mall.mbg.model.OmsCartItem;
import com.non.my_mall.mbg.model.PmsSkuStock;
import com.non.my_mall.service.OmsPromotionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OmsPromotionServiceImpl implements OmsPromotionService {

    @Resource
    private PmsProductDao productDao;
    @Override
    public List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList) {
        //1.先根据productId对CartItem进行分组，以spu为单位进行计算优惠
        Map<Long, List<OmsCartItem>> longListMap = groupCarItemByCpu(cartItemList);

        //2.查询所有商品的优惠相关信息
        List<PromotionProduct> promotionProductList = getPromotionProductList(cartItemList);
        //3.根据商品促销类型计算商品促销优惠价格
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        for (Map.Entry<Long, List<OmsCartItem>> entry:longListMap.entrySet()) {
            PromotionProduct promotionProductById = getPromotionProductById(entry.getKey(), promotionProductList);
            List<OmsCartItem> itemList = entry.getValue();

            handleNoReduce(cartPromotionItemList, itemList,promotionProductById);

        }
        return cartPromotionItemList;
    }

    /**
     * 以spu为单位对购物车中商品进行分组
     */
    public Map<Long, List<OmsCartItem>> groupCarItemByCpu(List<OmsCartItem> cartItemList) {
        TreeMap<Long,  List<OmsCartItem>> map = new TreeMap<>();
        for (OmsCartItem carItem: cartItemList) {
            List<OmsCartItem> omsCartItems = map.get(carItem.getProductId());
            
            if (omsCartItems == null) {
                omsCartItems = new ArrayList<>();
                omsCartItems.add(carItem);
                map.put(carItem.getProductId(), omsCartItems);
            } else {
                omsCartItems.add(carItem);
            }
        }
        return map;
    }

    /**
     * 查询所有商品的优惠相关信息
     */
    private List<PromotionProduct> getPromotionProductList(List<OmsCartItem> cartItemList) {
        List<Long> productIdList = new ArrayList<>();
        for(OmsCartItem cartItem:cartItemList){
            productIdList.add(cartItem.getProductId());
        }
        return productDao.getPromotionList(productIdList);
    }
    /**
     * 对没满足优惠条件的商品进行处理
     */
    private void handleNoReduce(List<CartPromotionItem> cartPromotionItemList, List<OmsCartItem> itemList,PromotionProduct promotionProduct) {
        for (OmsCartItem item : itemList) {
            CartPromotionItem cartPromotionItem = new CartPromotionItem();
            BeanUtils.copyProperties(item,cartPromotionItem);
            cartPromotionItem.setPromotionMessage("无优惠");
            cartPromotionItem.setReduceAmount(new BigDecimal(0));
            PmsSkuStock skuStock = getOriginalPrice(promotionProduct,item.getProductSkuId());
            if(skuStock!=null){
                cartPromotionItem.setRealStock(skuStock.getStock()-skuStock.getLockStock());
            }
            cartPromotionItem.setIntegration(promotionProduct.getGiftPoint());
            cartPromotionItem.setGrowth(promotionProduct.getGiftGrowth());
            cartPromotionItemList.add(cartPromotionItem);
        }
    }
    /**
     * 获取商品的原价
     */
    private PmsSkuStock getOriginalPrice(PromotionProduct promotionProduct, Long productSkuId) {
        for (PmsSkuStock skuStock : promotionProduct.getSkuStockList()) {
            if (productSkuId.equals(skuStock.getId())) {
                return skuStock;
            }
        }
        return null;
    }
    /**
     * 根据商品id获取商品的促销信息
     */
    private PromotionProduct getPromotionProductById(Long productId, List<PromotionProduct> promotionProductList) {
        for (PromotionProduct promotionProduct : promotionProductList) {
            if (productId.equals(promotionProduct.getId())) {
                return promotionProduct;
            }
        }
        return null;
    }
}
