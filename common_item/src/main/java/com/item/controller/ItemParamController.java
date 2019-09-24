package com.item.controller;

import com.common.utils.PageResult;
import com.item.service.ItemParamService;
import com.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品规格参数模板Controller
 */
@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    /**
     * 查询商品类目模板
     * @param itemCatId
     * @return
     */
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId){
        return itemParamService.selectItemParamByItemCatId(itemCatId);
    }

    /**
     * 查询商品规格参数模板并分页
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(Integer page,Integer rows){
        return itemParamService.selectItemParamAll(page, rows);
    }

    /**
     * 添加商品参数模板
     * @param tbItemParam
     * @return
     */
    @RequestMapping("/itemParamService")
    public  Integer insertItemParam(@RequestBody TbItemParam tbItemParam){
        return itemParamService.insertItemParam(tbItemParam);
    }

    /**
     * 删除商品参数模板
     * @param id
     * @return
     */
    @RequestMapping("/deleteItemParamById")
    public Integer deleteItemParamById(Long id){
        return itemParamService.deleteItemParamById(id);
    }
}
