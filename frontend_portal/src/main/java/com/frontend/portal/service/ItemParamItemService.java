package com.frontend.portal.service;

import com.common.utils.Result;
import org.springframework.stereotype.Service;

/**
 * 商品规格参数Service
 */
@Service
public interface ItemParamItemService {


    /**
     * 根据id查询商品规格参数
     * @param itemId
     * @return
     */
    Result selectTbItemParamItemByItemId(Long itemId);
}
