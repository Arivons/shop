package com.frontend.portal.controller;

import com.common.utils.Result;
import com.frontend.portal.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 商品描述controller
 */
@RestController
@RequestMapping("/frontend/item")
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;
    /**
     * 根据id查询商品描述
     * @param itemId
     * @return
     */
    @RequestMapping("/selectItemDescByItemId")
    public Result selectItemDescByItemId(Long itemId){
        try {
            return itemDescService.selectItemDescByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
}

