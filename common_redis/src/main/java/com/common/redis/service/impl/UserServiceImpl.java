package com.common.redis.service.impl;

import com.common.redis.service.UserService;
import com.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${user_session_redis_key}")
    private String USER_SESSION_REDIS_KEY;
    /**
     * 添加用户信息缓存
     * @param userToken
     * @param tbUser
     * @return
     */
    @Override
    public void insertUser(String userToken, TbUser tbUser) {
        tbUser.setPassword(null);
        redisTemplate.opsForValue().set(USER_SESSION_REDIS_KEY+":"+userToken, tbUser,1, TimeUnit.DAYS);
    }
    /**
     * 删除用户信息缓存
     * @param userToken
     */
    @Override
    public void deleteUser(String userToken) {
        redisTemplate.delete(USER_SESSION_REDIS_KEY+":"+userToken);
    }
    /**
     * 检查用户是否有效
     * @param token
     * @return
     */
    @Override
    public TbUser checkUser(String token) {
        return (TbUser) redisTemplate.opsForValue().get(USER_SESSION_REDIS_KEY+":"+token);
    }
}
