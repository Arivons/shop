package com.common.redis.service;

import com.pojo.TbItem;

/**
 * 商品信息缓存Service
 */
public interface ItemService {
    /**
     * 添加商品信息缓存
     * @param tbItem
     */
    void insertItem(TbItem tbItem);

    /**
     * 查询商品信息缓存
     * @param itemId
     * @return
     */
    TbItem findItemByItemId(Long itemId);
}
