package com.item.controller;

import com.common.utils.PageResult;
import com.item.service.ItemService;
import com.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 商品Controller
 */
@RestController
@RequestMapping("/service/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    /*
     * 查询所有商品并分页
     */
    @RequestMapping("/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(Integer page,Integer rows){
        return itemService.selectTbItemAllByPage(page, rows);
    }

    /**
     * 添加商品
     * @param tbItem
     * @return
     */
    @PostMapping("/insertItem")
    public Integer insertItem(@RequestBody TbItem tbItem){
        return itemService.insertItem(tbItem);
    }

    /**
     * 删除商品
     * @param tbItem
     * @return
     */
    @PostMapping("/deleteItemById")
    public Integer deleteItemById(@RequestBody TbItem tbItem){
        return itemService.updateItemById(tbItem);
    }
    /**
     * 预更新商品查询
     * @param itemId
     * @return
     */
    @PostMapping("/preUpdateItem")
    public Map<String,Object> preUpdateItem(Long itemId){
        return itemService.preUpdateItem(itemId);
    }

    /**
     * 跟新商品
     * @param tbItem
     * @return
     */
    @PostMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem){
     return  itemService.updateItemById(tbItem);
    }

    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    @PostMapping("/selectItemInfo")
    public TbItem selectItemInfo(Long itemId){
        return itemService.selectItemInfo(itemId);
    }
}
