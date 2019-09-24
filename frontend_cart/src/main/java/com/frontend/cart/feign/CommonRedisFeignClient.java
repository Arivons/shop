package com.frontend.cart.feign;

import com.common.utils.CartItem;
import com.pojo.TbUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("common-redis")
public interface CommonRedisFeignClient {
    /**
     * 缓存购物车
     * @param map
     */
    @RequestMapping("/redis/cart/insertCart")
    void insertCart(@RequestBody Map<String,Object> map);

    /**
     * 查询用户购物车缓存
     * @param userId
     * @return
     */
    @RequestMapping("/redis/cart/selectCart")
    Map<String, CartItem> selectCart(@RequestParam("userId") String userId);
    /**
     * 检查用户是否有效
     * @param token
     * @return
     */
    @RequestMapping("/redis/user/checkUser")
    TbUser checkUser(@RequestParam("token") String token);
}
