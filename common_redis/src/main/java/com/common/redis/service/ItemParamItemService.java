package com.common.redis.service;

import com.pojo.TbItemParamItem;
/**
 * 商品规格参数缓存Service
 */
public interface ItemParamItemService {
    /**
     * 添加商品规格参数缓存
     * @param tbItemParamItem
     */
    void insertItemParamItem(TbItemParamItem tbItemParamItem);

    /**
     * 查询商品规格参数缓存
     * @param itemId
     * @return
     */
    TbItemParamItem findTbItemParamItemByItemId(Long itemId);
}
