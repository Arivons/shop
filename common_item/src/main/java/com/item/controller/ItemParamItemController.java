package com.item.controller;

import com.item.service.ItemParamItemService;
import com.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品规格参数Controller
 */
@RestController
@RequestMapping("/service/itemParamItem")
public class ItemParamItemController {
    @Autowired
    private ItemParamItemService itemParamItemService;

    /**
     * 添加商品规格参数
     * @param tbItemParamItem
     * @return
     */
    @PostMapping("/insertItemParamItem")
    public Integer insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        return itemParamItemService.insertItemParamItem(tbItemParamItem);
    }

    /**
     * 跟新商品规格参数
     * @param tbItemParamItem
     * @return
     */
    @PostMapping("/updateItemParamItem")
    public  Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        return itemParamItemService.updateItemParamItem(tbItemParamItem);
    }

    /**
     * 根据id查询商品规格参数
     * @param itemId
     * @return
     */
    @PostMapping("/selectTbItemParamItemByItemId")
    public TbItemParamItem selectTbItemParamItemByItemId(Long itemId){
        return itemParamItemService.selectTbItemParamItemByItemId(itemId);
    }
}
