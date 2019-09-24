package com.common.redis.controller;

import com.common.redis.service.UserService;
import com.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 添加用户信息缓存
     * @param userToken
     * @param tbUser
     * @return
     */
    @RequestMapping("/insertUser")
    public void insertUser(@RequestParam String userToken,@RequestBody TbUser tbUser){
        userService.insertUser(userToken, tbUser);
    }

    /**
     * 删除用户信息缓存
     * @param userToken
     */
    @RequestMapping("/deleteUser")
    public void deleteUser(String userToken){
        userService.deleteUser(userToken);
    }
    /**
     * 检查用户是否有效
     * @param token
     * @return
     */
    @RequestMapping("/checkUser")
    public TbUser checkUser(String token){
        return userService.checkUser(token);
    }

}
