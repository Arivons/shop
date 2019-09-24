package com.frontend.sso.service.impl;

import com.common.utils.*;
import com.frontend.sso.feign.CommonRedisFeignClient;
import com.frontend.sso.service.UserService;
import com.mapper.TbUserMapper;
import com.pojo.TbUser;
import com.pojo.TbUserExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    @Value("${cookie_cart_item_name}")
    private String COOKIE_CART_ITEM_NAME;
    /**
     * 注册验证
     * @param checkValue
     * @param checkFlag 1:校验用户名是否存在 2:校验用户手机号是否存在
     * @return
     */
    @Override
    public Result checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        if(checkFlag==1){
            criteria.andUsernameEqualTo(checkValue);
        }else if(checkFlag==2){
            criteria.andPhoneEqualTo(checkValue);
        }else{
            return Result.error("数据已存在");
        }

        int count = tbUserMapper.countByExample(tbUserExample);
        if(count>0){
            return Result.error("数据已存在");
        }
        return Result.ok();
    }
    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    @Override
    public Result userRegister(TbUser tbUser) {
        String md5Password = MD5Utils.digest(tbUser.getPassword());
        tbUser.setPassword(md5Password);
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserMapper.insert(tbUser);
        return Result.ok();
    }
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response) {
        TbUser tbUser = this.login(username, password);
        if(tbUser==null){
          return Result.error("用户名或密码错误");
        }
        String userToken = UUID.randomUUID().toString();
        Integer status = this.insertUserToRedis(userToken, tbUser);
        if(status==500){
            return Result.error("登录失败");
        }
        Map<String,String > map = new HashMap<>();
        map.put("token", userToken);
        map.put("userid", tbUser.getId().toString());
        map.put("username", username);
         //同步购物车
        this.syncCart(tbUser.getId().toString(), request);
        //删除临时购物车
        this.deleteCookieCart(request, response);
        return Result.ok(map);
    }

    /**
     * 同步购物车
     * @param userId
     */
    private void syncCart(String userId,HttpServletRequest request){
        //获取永久用户购物车
        Map<String, CartItem> redisCart = this.getCart(userId);
        //获取临时购物车
        Map<String, CartItem> cookieCart = this.getCart(request);
        /**
         * 合并购物车
         */
        Set<String> keys = cookieCart.keySet();
        for (String key : keys) {
            redisCart.remove(key);
        }
        redisCart.putAll(cookieCart);
        /**
         * 将购物车保存到redis
         */
        this.saveCartToRedis(redisCart, userId);

    }
    /**
     * 获取永久用户购物车
     * @param userId
     * @return
     */
    private Map<String, CartItem> getCart(String userId){
        try {
            Map<String, CartItem> cartMap = commonRedisFeignClient.selectCart(userId);
            if(cartMap!=null){
                return cartMap;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new HashMap<String, CartItem>();
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
     * 将购物车保存到redis
     * @param cartMap
     * @param userId
     */
    private void saveCartToRedis(Map<String, CartItem> cartMap,String userId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("cart", cartMap);
        commonRedisFeignClient.insertCart(map);
    }

    /**
     * 删除临时购物车
     * @param request
     * @param response
     */
    private void deleteCookieCart(HttpServletRequest request, HttpServletResponse response){
        CookieUtils.deleteCookie(request, response, COOKIE_CART_ITEM_NAME);
    }
    /**
     * 退出登录
     * @param token
     * @return
     */
    @Override
    public Result logOut(String token) {
        this.commonRedisFeignClient.deleteUser(token);
        return Result.ok();
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    private TbUser login(String username, String password){
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.digest(password));
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        if(tbUsers!=null&&tbUsers.size()>0){
            return tbUsers.get(0);
        }
        return null;
    }

    /**
     * 插入用户信息到redis
     * @param userToken
     * @param user
     * @return
     */
    private Integer insertUserToRedis(String userToken,TbUser user){
        try {
            commonRedisFeignClient.insertUser(userToken, user);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 500;
    }
}
