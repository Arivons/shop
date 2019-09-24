package com.common.redis.service.impl;

import com.common.redis.service.ItemParamItemService;
import com.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${frontend_item_param_item_redis_key}")
    private String FRONTEND_ITEM_PARAM_ITEM_REDIS_KEY;
    /**
     * 添加商品规格参数缓存
     * @param tbItemParamItem
     */
    @Override
    public void insertItemParamItem(TbItemParamItem tbItemParamItem) {
        redisTemplate.opsForValue().set(FRONTEND_ITEM_PARAM_ITEM_REDIS_KEY+":"+tbItemParamItem.getItemId(), tbItemParamItem);
    }
    /**
     * 查询商品规格参数缓存
     * @param itemId
     * @return
     */
    @Override
    public TbItemParamItem findTbItemParamItemByItemId(Long itemId) {
        return (TbItemParamItem) redisTemplate.opsForValue().get(FRONTEND_ITEM_PARAM_ITEM_REDIS_KEY+":"+itemId);
    }
}
