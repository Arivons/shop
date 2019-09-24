package com.item.service;

import com.common.utils.PageResult;
import com.pojo.TbItem;

import java.util.Map;

/**
 * 商品
 */
public interface ItemService {
    /**
     * 查询所有商品并分页
     * @param page
     * @param rows
     * @return
     */
    PageResult selectTbItemAllByPage(Integer page,Integer rows);

    /**
     * 添加商品
     * @param tbItem
     * @return
     */
    Integer insertItem(TbItem tbItem);

    /**
     * 删除或跟新商品
     * @param tbItem
     * @return
     */
    Integer updateItemById(TbItem tbItem);
    /**
     * 预更新商品查询
     * @param itemId
     * @return
     */
    Map<String,Object> preUpdateItem(Long itemId);

    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    TbItem selectItemInfo(Long itemId);


}
