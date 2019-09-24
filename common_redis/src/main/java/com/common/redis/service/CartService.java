package com.common.redis.service;

import com.common.utils.CartItem;

import java.util.Map;

/**
 * 购物车缓存Service
 */
public interface CartService {
    /**
     * 缓存购物车
     * @param map
     */
    void insertCart(Map<String,Object> map);

    /**
     * 查询用户购物车缓存
     * @param userId
     * @return
     */
    Map<String, CartItem> selectCart(String userId);
}
