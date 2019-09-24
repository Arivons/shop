package com.common.redis.controller;

import com.common.redis.service.ItemParamItemService;
import com.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/itemParamItem")
public class ItemParamItemController {
    @Autowired
    private ItemParamItemService itemParamItemService;
    /**
     * 添加商品规格参数缓存
     * @param tbItemParamItem
     */
    @RequestMapping("/insertItemParamItem")
    public void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        itemParamItemService.insertItemParamItem(tbItemParamItem);
    }

    /**
     * 查询商品规格参数缓存
     * @param itemId
     * @return
     */
    @RequestMapping("/findTbItemParamItemByItemId")
    public TbItemParamItem findTbItemParamItemByItemId(Long itemId){
        return itemParamItemService.findTbItemParamItemByItemId(itemId);
    }
}
