package com.backend.controller;

import com.backend.service.ItemParamService;
import com.common.utils.Result;
import com.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品规格参数模板Controller
 */
@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    /**
     * 查询商品类目模板
     * @param itemCatId
     * @return
     */
    @GetMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable Long itemCatId){
        try {
            return itemParamService.selectItemParamByItemCatId(itemCatId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "出错了");
    }
    /**
     * 查询所有商品类目模板并分页
     * @return
     */
    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "2") Integer rows){
        try {
            return itemParamService.selectItemParamAll(page, rows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "出错了");
    }
    /**
     * 添加商品模板
     * @param tbItemParam
     * @return
     */
    @RequestMapping("/insertItemParam")
    public Result insertItemParam(TbItemParam tbItemParam){
        return itemParamService.insertItemParam(tbItemParam);
    }

    /**
     * 删除商品模板
     * @param id
     * @return
     */
    @RequestMapping("/deleteItemParamById")
    public Result  deleteItemParamById(Long id){
        return itemParamService.deleteItemParamById(id);
    }
}
