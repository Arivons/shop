package com.frontend.sso.controller;

import com.common.utils.Result;
import com.frontend.sso.service.UserService;
import com.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ssoController
 */
@RestController
@RequestMapping("/sso")
public class SSOController {
    @Autowired
    private UserService userService;
    /**
     * 注册验证
     * @param checkValue
     * @param checkFlag
     * @return
     */
    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable("checkValue") String checkValue,@PathVariable("checkFlag") Integer checkFlag){
        try {
            return userService.checkUserInfo(checkValue, checkFlag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    @RequestMapping("/userRegister")
    public Result userRegister(TbUser tbUser){
        try {
            return userService.userRegister(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/userLogin")
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try {
            return userService.userLogin(username, password,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
    /**
     * 退出登录
     * @param token
     * @return
     */
    @RequestMapping("/logOut")
    public Result logOut(String token){
        try {
            return userService.logOut(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
