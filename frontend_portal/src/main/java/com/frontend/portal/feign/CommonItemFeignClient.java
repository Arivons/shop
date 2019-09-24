package com.frontend.portal.feign;

import com.common.utils.CatResult;
import com.pojo.TbItem;
import com.pojo.TbItemDesc;
import com.pojo.TbItemParamItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("common-item")
public interface CommonItemFeignClient {
    /**
     * 查询前台商品分类
     * @return
     */
    @RequestMapping("/service/itemCat/selectItemCategoryAll")
    CatResult selectItemCategoryAll();
    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    @PostMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId")Long itemId);
    /**
     * 根据id查询商品描述
     * @param itemId
     * @return
     */
    @PostMapping("/service/itemDesc/selectItemDescByItemId")
    TbItemDesc selectItemDescByItemId(@RequestParam("itemId")Long itemId);
    /**
     * 根据id查询商品规格参数
     * @param itemId
     * @return
     */
    @PostMapping("/service/itemParamItem/selectTbItemParamItemByItemId")
    TbItemParamItem selectTbItemParamItemByItemId(@RequestParam("itemId") Long itemId);
}
