package com.common.redis.service;

import com.pojo.TbUser;

public interface UserService {
    /**
     * 添加用户信息缓存
     * @param userToken
     * @param tbUser
     * @return
     */
    void insertUser(String userToken, TbUser tbUser);

    /**
     * 删除用户信息缓存
     * @param userToken
     */
    void deleteUser(String userToken);

    /**
     * 检查用户是否有效
     * @param token
     * @return
     */
    TbUser checkUser(String token);
}
