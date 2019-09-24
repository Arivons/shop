package com.common.redis.service.impl;

import com.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {
    @Value("${frontend_ad_redis_key}")
    private String FRONTEND_AD_REDIS_KEY;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 插入首页大广告缓存
     * @param list
     */
    @Override
    public void insertContentAD(List<Map> list) {
        redisTemplate.opsForValue().set(FRONTEND_AD_REDIS_KEY, list);
    }
    /**
     * 查询首页大广告缓存
     * @return
     */
    @Override
    public List<Map> selectContentAD() {
        return (List<Map>) redisTemplate.opsForValue().get(FRONTEND_AD_REDIS_KEY);
    }
}
