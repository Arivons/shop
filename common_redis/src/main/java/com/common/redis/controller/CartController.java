package com.common.redis.controller;

import com.common.redis.service.CartService;
import com.common.utils.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/redis/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    /**
     * 缓存购物车
     * @param map
     */
    @RequestMapping("/insertCart")
    public void insertCart(@RequestBody Map<String,Object> map){
        cartService.insertCart(map);
    }
    /**
     * 查询用户购物车缓存
     * @param userId
     * @return
     */
    @RequestMapping("/selectCart")
    public Map<String, CartItem> selectCart(String userId){
        return cartService.selectCart(userId);
    }

}
