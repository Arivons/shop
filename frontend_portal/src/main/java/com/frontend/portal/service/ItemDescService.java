package com.frontend.portal.service;

import com.common.utils.Result;
import org.springframework.stereotype.Service;

/**
 * 商品描述Service
 */
@Service
public interface ItemDescService {

    /**
     * 根据id查询商品描述
     * @param itemId
     * @return
     */
    Result selectItemDescByItemId(Long itemId);
}
