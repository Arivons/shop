package com.frontend.portal.controller;

import com.common.utils.Result;
import com.frontend.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 商品controller
 */
@RestController
@RequestMapping("/frontend/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(Long itemId){
        try {
            return itemService.selectItemInfo(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
       return Result.error("error");
    }
}
