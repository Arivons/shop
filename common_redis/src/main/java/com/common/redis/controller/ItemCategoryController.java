package com.common.redis.controller;

import com.common.redis.service.ItemCategoryService;
import com.common.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/itemCategory")
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;
    /**
     * 添加首页商品分类缓存
     * @param catResult
     */
    @RequestMapping("/insertItemCategory")
    public void insertItemCategory(@RequestBody CatResult catResult){
        itemCategoryService.insertItemCatgory(catResult);
    }

    /**
     * 查询首页商品分类缓存
     * @return
     */
    @RequestMapping("/selectItemCategory")
    public CatResult selectItemCategory(){
        return itemCategoryService.selectItemCatgory();
    }
}
