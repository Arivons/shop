package com.backend.feign;

import com.backend.fallback.CommonItemFeignClientFallBackFactory;
import com.common.utils.PageResult;
import com.pojo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
 *  商品服务
 */
@FeignClient(value = "common-item",fallback = CommonItemFeignClientFallBackFactory.class)
public interface CommonItemFeignClient {
    /*
     * 查询所有商品并分页
     */
    @GetMapping("/service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows);
    /**
     * 根据id查询子节点
     * @param id
     * @return
     */
    @PostMapping("/service/itemCat/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam("id") Long id);
    /**
     * 查询商品类目模板
     * @param itemCatId
     * @return
     */
    @GetMapping("/service/itemParam/selectItemParamByItemCatId")
    TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId);
    /**
     * 添加商品
     * @param tbItem
     * @return
     */
    @PostMapping("/service/item/insertItem")
    Integer insertItem(@RequestBody TbItem tbItem);

    /**
     * 添加商品描述
     * @param tbItemDesc
     * @return
     */
    @PostMapping("/service/itemDesc/insertItemDesc")
    Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc);
    /**
     * 添加商品规格参数
     * @param tbItemParamItem
     * @return
     */
    @PostMapping("/service/itemParamItem/insertItemParamItem")
    Integer insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);
    /**
     * 删除商品
     * @param tbItem
     * @return
     */
    @PostMapping("/service/item/deleteItemById")
    Integer deleteItemById(@RequestBody TbItem tbItem);
    /**
     * 预更新商品查询
     * @param itemId
     * @return
     */
    @PostMapping("/service/item/preUpdateItem")
    Map<String,Object> preUpdateItem(@RequestParam("itemId") Long itemId);

    /**
     * 跟新商品
     * @param tbItem
     * @return
     */
    @PostMapping("/service/item/updateTbItem")
    Integer updateTbItem(@RequestBody TbItem tbItem);
    /**
     * 跟新商品描述
     * @param tbItemDesc
     * @return
     */
    @PostMapping("/service/itemDesc/updateItemDesc")
    Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc);
    /**
     * 跟新商品规格参数
     * @param tbItemParamItem
     * @return
     */
    @PostMapping("/service/itemParamItem/updateItemParamItem")
    Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);
    /**
     * 查询商品规格参数模板并分页
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/service/itemParam/selectItemParamAll")
    PageResult selectItemParamAll(@RequestParam("page") Integer page,@RequestParam("rows")Integer rows);
    /**
     * 添加商品参数模板
     * @param tbItemParam
     * @return
     */
    @RequestMapping("/service/itemParam/itemParamService")
    Integer insertItemParam(@RequestBody TbItemParam tbItemParam);

    /**
     * 删除商品参数模板
     * @param id
     * @return
     */
    @RequestMapping("/service/itemParam/deleteItemParamById")
    Integer deleteItemParamById(@RequestParam("id") Long id);

}
