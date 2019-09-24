package com.frontend.order.feign;

import com.common.utils.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("common-redis")
public interface CommonRedisFeignClient {
    /**
     * 生成订单ID
     * @return
     */
    @RequestMapping("/redis/order/selectOrderId")
    Long selectOrderId();
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
}
