package com.frontend.portal.service;

import com.common.utils.Result;

/**
 * 商品ItemService
 */
public interface ItemService {

    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    Result selectItemInfo(Long itemId);


}
