package com.frontend.cart.service;

import com.common.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未登录购物车Service
 */
public interface CookieCartService {
    /**
     * 未登录下状态将商品加入购物车
     */
    void addItemToCookieCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
    /**
     * 显示购物车列表
     * @return
     */
    Result showCart(HttpServletRequest request);

    /**
     * 修改购物车商品数量
     * @param num
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    Result updateItemNum(Integer num,Long itemId,HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除购物车商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    Result deleteItemFromCart(Long itemId,HttpServletRequest request, HttpServletResponse response);
}
