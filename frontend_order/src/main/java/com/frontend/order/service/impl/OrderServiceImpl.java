package com.frontend.order.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.utils.CartItem;
import com.common.utils.IDUtils;
import com.common.utils.Result;
import com.frontend.order.feign.CommonRedisFeignClient;
import com.frontend.order.service.OrderService;
import com.mapper.TbOrderItemMapper;
import com.mapper.TbOrderMapper;
import com.mapper.TbOrderShippingMapper;
import com.pojo.TbOrder;
import com.pojo.TbOrderItem;
import com.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;
    /**
     * 保存订单
     * @param orderItems
     * @param userId
     * @param tbOrderShipping
     * @return
     */
    @LcnTransaction
    @Override
    public Result insertOrder(List<TbOrderItem> orderItems, String userId, TbOrderShipping tbOrderShipping) {
        Long orderId = commonRedisFeignClient.selectOrderId();
        Date date = new Date();
        TbOrder order = new TbOrder();
        order.setOrderId(orderId.toString());
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        order.setStatus(1);
        order.setCreateTime(date);
        order.setUpdateTime(date);
        //0:未评价 1：已评价
        order.setBuyerRate(0);
        //将订单插入到数据库中
        this.tbOrderMapper.insert(order);

        List<String> itemIds = new ArrayList<>();
        /**
         * 插入订单商品数据
         */
        for (TbOrderItem orderItem : orderItems) {
            itemIds.add(orderItem.getItemId());
            orderItem.setId(IDUtils.genItemId()+"");
            orderItem.setOrderId(orderId.toString());
            this.tbOrderItemMapper.insert(orderItem);
        }
        //插入物流表数据
        tbOrderShipping.setOrderId(orderId.toString());
        tbOrderShipping.setUpdated(date);
        tbOrderShipping.setCreated(date);
        this.tbOrderShippingMapper.insert(tbOrderShipping);
        /**
         * 删除购物车
         */
        this.deleteItemFromCart(itemIds, userId);
        return Result.ok(orderId.toString());
    }
    /**
     * 获取用户购物车
     * @param userId
     * @return
     */
    private Map<String, CartItem> getCart(String userId){
        try {
            Map<String, CartItem> cartMap = commonRedisFeignClient.selectCart(userId);
            if(cartMap!=null){
                return cartMap;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new HashMap<String, CartItem>();
    }

    /**
     * 删除购物车商品
     * @param itemIds
     * @param userId
     * @return
     */
    private void deleteItemFromCart(List<String> itemIds, String userId) {
        Map<String, CartItem> cart = this.getCart(userId);
        for (String itemId : itemIds) {
            cart.remove(itemId);
        }
        this.saveCartToRedis(cart, userId);
    }
    /**
     * 将购物车保存到redis
     * @param cartMap
     * @param userId
     */
    private void saveCartToRedis(Map<String, CartItem> cartMap,String userId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("cart", cartMap);
        commonRedisFeignClient.insertCart(map);
    }

}
