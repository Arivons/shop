package com.item.service;

import com.pojo.TbItemParamItem;
import org.springframework.stereotype.Service;

/**
 * 商品规格参数Service
 */
@Service
public interface ItemParamItemService {
    /**
     * 添加商品规格参数
     * @param tbItemParamItem
     * @return
     */
    Integer insertItemParamItem(TbItemParamItem tbItemParamItem);

    /**
     * 跟新商品规格参数
     * @param tbItemParamItem
     * @return
     */
    Integer updateItemParamItem(TbItemParamItem tbItemParamItem);

    /**
     * 根据id查询商品规格参数
     * @param itemId
     * @return
     */
    TbItemParamItem selectTbItemParamItemByItemId(Long itemId);
}
