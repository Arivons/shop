package com.frontend.cart.service;

import com.pojo.TbUser;

public interface UserCheckService {
    /**
     * 检查用户是否有效
     * @param token
     * @return
     */
    TbUser checkUser(String token);
}
