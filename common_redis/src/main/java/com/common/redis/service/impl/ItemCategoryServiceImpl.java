package com.common.redis.service.impl;

import com.common.redis.service.ItemCategoryService;
import com.common.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${frontend_item_category_redis_key}")
    private String FRONTEND_ITEM_CATEGORY_KEY;
    /**
     * 添加首页商品分类缓存
     * @param catResult
     */
    @Override
    public void insertItemCatgory(CatResult catResult) {
        redisTemplate.opsForValue().set(FRONTEND_ITEM_CATEGORY_KEY, catResult);
    }
    /**
     * 查询首页商品分类缓存
     * @return
     */
    @Override
    public CatResult selectItemCatgory() {
        return (CatResult) redisTemplate.opsForValue().get(FRONTEND_ITEM_CATEGORY_KEY);
    }
}
