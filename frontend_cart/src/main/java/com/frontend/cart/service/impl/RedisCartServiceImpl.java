package com.frontend.cart.service.impl;

import com.common.utils.CartItem;
import com.common.utils.Result;
import com.frontend.cart.feign.CommonItemFeignClient;
import com.frontend.cart.feign.CommonRedisFeignClient;
import com.frontend.cart.service.RedisCartService;
import com.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RedisCartServiceImpl implements RedisCartService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    /**
     * 已登录下状态将商品加入购物车
     * @param itemId
     * @param userId
     * @param num
     * @return
     */
    @Override
    public Result addItem(Long itemId, String userId, Integer num) {
        //1.获取用户购物车
        Map<String, CartItem> cart = this.getCart(userId);
        //2.获取商品信息
        TbItem item = this.getItemInfoByItemId(itemId);
        //3.将商品加入购物车
        Map<String, CartItem> cartMap = this.addItemTORedisCart(cart, item, num);
        //4.将购物车保存到redis
        this.saveCartToRedis(cartMap, userId);
        return Result.ok();
    }
    /**
     * 显示购物车列表
     * @return
     */
    @Override
    public Result showCart(String userId) {
        List<CartItem> list = new ArrayList<>();
        Map<String, CartItem> cart = this.getCart(userId);
        Set<String> keys = cart.keySet();
        for (String key : keys) {
            list.add(cart.get(key));
        }
        return Result.ok(list);
    }

    /**
     * 修改购物车商品数量
     * @param num
     * @param itemId
     * @param userId
     * @return
     */
    @Override
    public Result updateItemNum(Integer num, Long itemId, String userId) {
        Map<String, CartItem> cart = this.getCart(userId);
        CartItem cartItem = cart.get(itemId.toString());
        if(cartItem!=null){
            cartItem.setNum(num);
            this.saveCartToRedis(cart, userId);
        }

        return Result.ok();
    }
    /**
     * 删除购物车商品
     * @param itemId
     * @param userId
     * @return
     */
    @Override
    public Result deleteItemFromCart(Long itemId, String userId) {
        Map<String, CartItem> cart = this.getCart(userId);
        cart.remove(itemId.toString());
        this.saveCartToRedis(cart, userId);
        return Result.ok();
    }

    /**
     * 获取结算页面商品数据
     * @param ids
     * @param userId
     * @return
     */
    @Override
    public Result goSettlement(String [] ids, String userId) {
        List<CartItem> list = new ArrayList<>();
        Map<String, CartItem> cart = this.getCart(userId);
        for (String id:ids){
            list.add(cart.get(id));
        }
        return Result.ok(list);
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
     * 获取商品信息
     * @param itemId
     * @return
     */
    private TbItem getItemInfoByItemId(Long itemId){
        return commonItemFeignClient.selectItemInfo(itemId);
    }
    /**
     * 将商品加入购物车
     */
    private Map<String,CartItem> addItemTORedisCart( Map<String, CartItem> cart,TbItem item,Integer num){
        CartItem cartItem = cart.get(item.getId().toString());
        /**
         * 判断购物车是否已经有当前商品
         */
        if(cartItem==null){
            CartItem ItemCart = new CartItem();
            ItemCart.setId(item.getId());
            ItemCart.setImage(item.getImage());
            ItemCart.setNum(num);
            ItemCart.setSellPoint(item.getSellPoint());
            ItemCart.setTitle(item.getTitle());
            ItemCart.setPrice(item.getPrice());
            cart.put(item.getId().toString(), ItemCart);
        }else{
            cartItem.setNum(cartItem.getNum()+num);
            cart.put(item.getId().toString(), cartItem);
        }
        return  cart;
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
