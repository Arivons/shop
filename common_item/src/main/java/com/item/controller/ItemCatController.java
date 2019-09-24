package com.item.controller;

import com.common.utils.CatResult;
import com.item.service.ItemCatService;
import com.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
 *商品类目Controller
 */
@RestController
@RequestMapping("/service/itemCat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    /**
     * 根据商品类目id查询子节点
     * @param id
     * @return
     */
    @PostMapping("/selectItemCategoryByParentId")
    public List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id){
        return itemCatService.selectItemCategoryByParentId(id);
    }
    /**
     * 查询前台商品分类
     * @return
     */
    @RequestMapping("/selectItemCategoryAll")
    public CatResult selectItemCategoryAll(){
        return itemCatService.selectItemCategoryAll();
    }
}
