package com.backend.controller;

import com.backend.service.ItemService;
import com.common.utils.Result;
import com.pojo.TbItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backend/item")
@RefreshScope
public class ItemController {
    Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Value("${msg}")
    private String msg;
    @Autowired
    private ItemService itemService;
    @GetMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "2") Integer rows){
        System.out.println(msg);
        try {
            return itemService.selectTbItemAllByPage(page, rows);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Result.build(500, "出错了");
    }
    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams){
        try {
            return itemService.insertItem(tbItem, desc, itemParams);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Result.build(500, "出错了");
        }
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long itemId){

            return itemService.deleteItemById(itemId);
    }
    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(Long itemId){
        try {
            return itemService.preUpdateItem(itemId);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Result.build(500, "出错了");
    }
    @RequestMapping("/updateTbItem")
    public Result preUpdateItem(TbItem tbItem, String desc, String itemParams){
        try {
            return itemService.updateTbItem(tbItem, desc, itemParams);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Result.build(500, "跟新失败");
    }
}

