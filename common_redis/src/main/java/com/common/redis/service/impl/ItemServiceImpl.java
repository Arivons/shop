package com.common.redis.service.impl;

import com.common.redis.service.ItemService;
import com.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${frontend_item_info_redis_key}")
    private String FRONTEND_ITEM_INFO_REDIS_KEY;
    /**
     * 添加商品信息缓存
     * @param tbItem
     */
    @Override
    public void insertItem(TbItem tbItem) {
        redisTemplate.opsForValue().set(FRONTEND_ITEM_INFO_REDIS_KEY+":"+tbItem.getId(), tbItem);
    }
    /**
     * 查询商品信息缓存
     * @param itemId
     * @return
     */
    @Override
    public TbItem findItemByItemId(Long itemId) {

        return (TbItem) redisTemplate.opsForValue().get(FRONTEND_ITEM_INFO_REDIS_KEY+":"+itemId);
    }
}
