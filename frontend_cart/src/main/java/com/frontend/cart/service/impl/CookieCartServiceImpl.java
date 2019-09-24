package com.frontend.cart.service.impl;

import com.common.utils.CartItem;
import com.common.utils.CookieUtils;
import com.common.utils.JsonUtils;
import com.common.utils.Result;
import com.frontend.cart.feign.CommonItemFeignClient;
import com.frontend.cart.service.CookieCartService;
import com.pojo.TbItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class CookieCartServiceImpl implements CookieCartService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Value("${cookie_cart_item_name}")
    private String COOKIE_CART_ITEM_NAME;
    @Override
    public void addItemToCookieCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(itemId);
        //1.获取临时购物车
        Map<String, CartItem> cart = this.getCart(request);
        //2.获取商品信息
        TbItem item = this.findItemByItemId(itemId);
        //3.将商品加入购物车
        Map<String, CartItem> CartItemMap = this.addItemToCart(cart, item, num);
        //4.将购物车写入cookie
        this.addCartToCookie(request, response, CartItemMap);

    }



    /**
     * 获取临时购物车
     * @param request
     * @return
     */
    private Map<String, CartItem> getCart(HttpServletRequest request){
        String cartJson = CookieUtils.getCookieValue(request, COOKIE_CART_ITEM_NAME, true);
        //cookie有购物车的情况
        try {
            if(!StringUtils.isBlank(cartJson)){
                return JsonUtils.jsonToMap(cartJson, CartItem.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //cookie没有购物车的情况
        return new HashMap<String, CartItem>();
    }

    /**
     * 获取商品信息
     * @param itemId
     * @return
     */
    private TbItem findItemByItemId(Long itemId){
        return commonItemFeignClient.selectItemInfo(itemId);
    }

    /**
     * 将商品加入购物车
     * @param map
     * @param item
     * @param num
     * @return
     */
    private Map<String, CartItem> addItemToCart(Map<String, CartItem> map,TbItem item,Integer num){
        CartItem cartItem = map.get(item.getId().toString());
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
            map.put(item.getId().toString(), ItemCart);
        }else{
            cartItem.setNum(cartItem.getNum()+num);
            map.put(item.getId().toString(), cartItem);
        }
        return  map;
    }

    /**
     * 将购物车写入cookie
     * @param request
     * @param response
     * @param map
     * @return
     */
    private void addCartToCookie( HttpServletRequest request, HttpServletResponse response,Map<String, CartItem> map){
        try {
            String cartJson = JsonUtils.objectToJson(map);
            CookieUtils.setCookie(request, response, COOKIE_CART_ITEM_NAME,cartJson,true );
        }catch (Exception e){
           e.printStackTrace();
        }

    }
    /**
     * 显示购物车列表
     * @param
     * @return
     */
    @Override
    public Result showCart(HttpServletRequest request) {
        List<CartItem> list = new ArrayList<>();
        Map<String, CartItem> cart = this.getCart(request);
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
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result updateItemNum(Integer num, Long itemId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, CartItem> cart = this.getCart(request);
        CartItem cartItem = cart.get(itemId.toString());
        if(cartItem!=null){
            cartItem.setNum(num);
           // cart.put(itemId.toString(), cartItem);
            this.addCartToCookie(request, response, cart);
        }

        return Result.ok();
    }
    /**
     * 删除购物车商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result deleteItemFromCart(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, CartItem> cart = this.getCart(request);
        cart.remove(itemId.toString());
        this.addCartToCookie(request, response, cart);
        return Result.ok();
    }
}
