package com.common.redis.service.impl;

import com.common.redis.service.CartService;
import com.common.utils.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${cart_item_redis_key}")
    private String CART_ITEM_REDIS_KEY;
    /**
     * 缓存购物车
     * @param map
     */
    @Override
    public void insertCart(Map<String, Object> map) {
        String userId = (String) map.get("userId");
        Map<String, CartItem> carts = (Map<String, CartItem>) map.get("cart");
        redisTemplate.opsForHash().put(CART_ITEM_REDIS_KEY,userId, carts);
    }
    /**
     * 查询用户购物车缓存
     * @param userId
     * @return
     */
    @Override
    public Map<String, CartItem> selectCart(String userId) {
        return (Map<String, CartItem>) redisTemplate.opsForHash().get(CART_ITEM_REDIS_KEY, userId);
    }
}
