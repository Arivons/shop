package com.frontend.portal.feign;

import com.common.utils.CatResult;
import com.pojo.TbItem;
import com.pojo.TbItemDesc;
import com.pojo.TbItemParamItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("common-redis")
public interface CommonRedisFeignClient {
    /**
     * 插入首页大广告缓存
     * @param list
     */
    @RequestMapping("/redis/content/insertContentAD")
    void insertContentAD(@RequestBody List<Map> list);
    /**
     * 查询首页大广告缓存
     * @return
     */
    @RequestMapping("/redis/content/selectContentAD")
    List<Map> selectContentAD();
    /**
     * 添加首页商品分类缓存
     * @param catResult
     */
    @RequestMapping("/redis/itemCategory/insertItemCategory")
    void insertItemCategory(@RequestBody CatResult catResult);
    /**
     * 查询首页商品分类缓存
     * @return
     */
    @RequestMapping("/redis/itemCategory/selectItemCategory")
    CatResult selectItemCategory();
    /**
     * 添加商品信息缓存
     * @param tbItem
     */
    @RequestMapping("/redis/item/insertItem")
    void insertItem(@RequestBody TbItem tbItem);

    /**
     * 查询商品信息缓存
     * @param itemId
     * @return
     */
    @RequestMapping("/redis/item/findItemByItemId")
    TbItem findItemByItemId(@RequestParam("itemId") Long itemId);
    /**
     * 添加商品规格参数缓存
     * @param tbItemParamItem
     */
    @RequestMapping("/redis/itemParamItem/insertItemParamItem")
    void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    /**
     * 查询商品规格参数缓存
     * @param itemId
     * @return
     */
    @RequestMapping("/redis/itemParamItem/findTbItemParamItemByItemId")
    TbItemParamItem findTbItemParamItemByItemId(@RequestParam("itemId") Long itemId);
    /**
     * 添加商品描述缓存
     * @param tbItemDesc
     */
    @RequestMapping("/redis/itemDesc/insertItemDesc")
    void insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    /**
     * 查询商品描述缓存
     * @param itemId
     * @return
     */
    @RequestMapping("/redis/itemDesc/findItemDescByItemId")
    TbItemDesc findItemDescByItemId(@RequestParam("itemId")Long itemId);
}
