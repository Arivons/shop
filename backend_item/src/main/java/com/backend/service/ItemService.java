package com.backend.service;

import com.common.utils.Result;
import com.pojo.TbItem;

/**
 * 商品Service
 */
public interface ItemService {
    /*
     * 查询所有商品并分页
     */
    Result selectTbItemAllByPage(Integer page, Integer rows);

    /**
     *添加商品
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    Result insertItem(TbItem tbItem,String desc,String itemParams);
    /**
     * 删除商品
     * @param itemId
     * @return
     */
    Result deleteItemById(Long itemId);
    /**
     * 预更新商品查询
     * @param itemId
     * @return
     */
    Result preUpdateItem( Long itemId);

    /**
     * 跟新商品
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    Result updateTbItem(TbItem tbItem,String desc,String itemParams);
}
