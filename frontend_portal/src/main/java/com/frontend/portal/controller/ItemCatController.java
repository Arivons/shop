package com.frontend.portal.controller;

import com.common.utils.Result;
import com.frontend.portal.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/itemCategory")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    /**
     * 查询前台商品分类
     * @return
     */
    @RequestMapping("/selectItemCategoryAll")
    public Result selectItemCategoryAll(){
        try {
            return itemCatService.selectItemCategoryAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
