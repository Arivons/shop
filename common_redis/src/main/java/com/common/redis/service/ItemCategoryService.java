package com.common.redis.service;

import com.common.utils.CatResult;

/**
 * 首页商品分类缓存
 */
public interface ItemCategoryService {
    /**
     * 添加首页商品分类缓存
     * @param catResult
     */
    void insertItemCatgory(CatResult catResult);

    /**
     * 查询首页商品分类缓存
     * @return
     */
    CatResult selectItemCatgory();
}
