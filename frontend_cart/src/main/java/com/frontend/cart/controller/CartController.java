package com.frontend.cart.controller;

import com.common.utils.Result;
import com.frontend.cart.service.CookieCartService;
import com.frontend.cart.service.RedisCartService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CookieCartService cookieCartService;
    @Autowired
    private RedisCartService redisCartService;

    /**
     * 添加商品到购物车
     * @param itemId
     * @param userId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addItem")
    public Result addItem(Long itemId, String userId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(userId)){
                //未登录的情况
                cookieCartService.addItemToCookieCart(itemId, num, request, response);
                return Result.ok();
            }else {
                //已登录情况
                return redisCartService.addItem(itemId, userId, num);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.error("error");
    }
    /**
     * 显示购物车列表
     * @param userId
     * @return
     */
    @RequestMapping("/showCart")
    public Result showCart(String userId,HttpServletRequest request){
        try {
            if(StringUtils.isBlank(userId)){
                //未登录情况
                return cookieCartService.showCart(request);
            }else {
                //已登录情况
                return redisCartService.showCart(userId);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.error("error");
    }

    /**
     * 修改购物车商品数量
     * @param num
     * @param userId
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateItemNum")
    public Result updateItemNum(Integer num,String userId,Long itemId,HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(userId)){
                //未登录情况
                return cookieCartService.updateItemNum(num, itemId, request, response);
            }else {
                //已登录情况
                return redisCartService.updateItemNum(num, itemId, userId);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.error("error");
    }

    /**
     * 删除购物车商品
     * @param itemId
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteItemFromCart")
    public Result deleteItemFromCart(Long itemId,String userId,HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(userId)){
                //未登录情况
                return cookieCartService.deleteItemFromCart(itemId, request, response);
            }else {
                //已登录情况
                return redisCartService.deleteItemFromCart(itemId, userId);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.error("error");
    }


    /**
     * 获取结算页面数据
     * @param ids
     * @param userId
     * @return
     */
    @RequestMapping("/goSettlement")
    public Result goSettlement(String [] ids,String userId){
        try {
            return redisCartService.goSettlement(ids, userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }

}
