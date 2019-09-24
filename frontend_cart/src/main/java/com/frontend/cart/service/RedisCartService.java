package com.frontend.cart.service;

import com.common.utils.Result;

/**
 * 已登录购物车Service
 */
public interface RedisCartService {
    /**
     * 已登录下状态将商品加入购物车
     * @param itemId
     * @param userId
     * @param num
     * @return
     */
    Result addItem(Long itemId, String userId, Integer num);
    /**
     * 显示购物车列表
     * @return
     */
    Result showCart(String userId);

    /**
     * 修改购物车商品数量
     * @param num
     * @param itemId
     * @param userId
     * @return
     */
    Result updateItemNum(Integer num,Long itemId,String userId);

    /**
     * 删除购物车商品
     * @param itemId
     * @param userId
     * @return
     */
    Result deleteItemFromCart(Long itemId,String userId);

    /**
     * 获取结算页面商品数据
     * @param ids
     * @param userId
     * @return
     */
    Result goSettlement(String [] ids,String userId);
}
