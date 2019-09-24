package com.frontend.portal.controller;

import com.common.utils.Result;
import com.frontend.portal.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品规格参数controller
 */
@RestController
@RequestMapping("/frontend/item")
public class ItemParamItemController {
    @Autowired
    private ItemParamItemService itemParamItemService;
    /**
     * 根据id查询商品规格参数
     * @param itemId
     * @return
     */
    @RequestMapping("/selectTbItemParamItemByItemId")
    public Result selectTbItemParamItemByItemId(Long itemId){
        try {
            return itemParamItemService.selectTbItemParamItemByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }


}
