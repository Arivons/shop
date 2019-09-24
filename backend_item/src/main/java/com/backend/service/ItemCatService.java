package com.backend.service;

import com.common.utils.Result;
/**
 * 商品类目Service
 */
public interface ItemCatService {
    /**
     * 根据id查询子节点
     * @param id
     * @return
     */
    Result selectItemCategoryByParentId(Long id);

}
