package com.common.redis.controller;

import com.common.redis.service.ItemService;
import com.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    /**
     * 添加商品信息缓存
     * @param tbItem
     */
    @RequestMapping("/insertItem")
    public void insertItem(@RequestBody TbItem tbItem){
        itemService.insertItem(tbItem);
    }

    /**
     * 查询商品信息缓存
     * @param itemId
     * @return
     */
    @RequestMapping("/findItemByItemId")
    public TbItem findItemByItemId(Long itemId){
        return itemService.findItemByItemId(itemId);
    }
}
