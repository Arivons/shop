package com.common.redis.service.impl;

import com.common.redis.service.ItemDescService;
import com.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${frontend_item_desc_redis_key}")
    private String FRONTEND_ITEM_DESC_REDIS_KEY;
    /**
     * 添加商品描述缓存
     * @param tbItemDesc
     */
    @Override
    public void insertItemDesc(TbItemDesc tbItemDesc) {
        redisTemplate.opsForValue().set(FRONTEND_ITEM_DESC_REDIS_KEY+":"+tbItemDesc.getItemId(), tbItemDesc);
    }
    /**
     * 查询商品描述缓存
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc findItemDescByItemId(Long itemId) {
        return (TbItemDesc) redisTemplate.opsForValue().get(FRONTEND_ITEM_DESC_REDIS_KEY+":"+itemId);
    }
}
