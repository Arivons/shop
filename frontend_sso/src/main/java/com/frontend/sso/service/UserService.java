package com.frontend.sso.service;

import com.common.utils.Result;
import com.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户Service
 */
public interface UserService {
    /**
     * 注册验证
     * @param checkValue
     * @param checkFlag 1:校验用户名是否存在 2:校验用户手机号是否存在
     * @return
     */
    Result checkUserInfo(String checkValue, Integer checkFlag);
    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    Result userRegister(TbUser tbUser);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    Result userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logOut(String token);
}
