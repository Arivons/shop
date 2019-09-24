package com.backend.controller;

import com.backend.service.ItemCatService;
import com.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品类目Controller
 */
@RestController
@RequestMapping("/backend/itemCategory")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping("/selectItemCategoryByParentId")
    public Result selectItemCategoryByParentId(@RequestParam(defaultValue = "0") Long id){
        try {
            return itemCatService.selectItemCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "出错了");
    }
}
