package com.frontend.sso.feign;

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
     * 添加用户信息缓存
     * @param userToken
     * @param tbUser
     * @return
     */
    @RequestMapping("/redis/user/insertUser")
    void insertUser(@RequestParam("userToken") String userToken, @RequestBody TbUser tbUser);

    /**
     * 删除用户信息缓存
     * @param userToken
     */
    @RequestMapping("/redis/user/deleteUser")
    void deleteUser(@RequestParam("userToken") String userToken);
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
