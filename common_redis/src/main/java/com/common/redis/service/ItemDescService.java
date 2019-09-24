package com.common.redis.service;

import com.pojo.TbItemDesc;

/**
 * 商品描述缓存Service
 */
public interface ItemDescService {
    /**
     * 添加商品描述缓存
     * @param tbItemDesc
     */
    void insertItemDesc(TbItemDesc tbItemDesc);

    /**
     * 查询商品描述缓存
     * @param itemId
     * @return
     */
    TbItemDesc findItemDescByItemId(Long itemId);
}
