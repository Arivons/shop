package com.frontend.cart.service.impl;

import com.frontend.cart.feign.CommonRedisFeignClient;
import com.frontend.cart.service.UserCheckService;
import com.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCheckServiceImpl implements UserCheckService {
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    /**
     * 检查用户是否有效
     * @param token
     * @return
     */
    @Override
    public TbUser checkUser(String token) {
        return commonRedisFeignClient.checkUser(token);
    }
}
