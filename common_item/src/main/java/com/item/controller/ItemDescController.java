package com.item.controller;

import com.item.service.ItemDescService;
import com.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品描述controller
 */
@RestController
@RequestMapping("/service/itemDesc")
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;

    /**
     * 添加商品描述
     * @param tbItemDesc
     * @return
     */
    @PostMapping("/insertItemDesc")
    public Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return itemDescService.insertItemDesc(tbItemDesc);
    }

    /**
     * 跟新商品描述
     * @param tbItemDesc
     * @return
     */
    @PostMapping("/updateItemDesc")
    public Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return itemDescService.updateItemDesc(tbItemDesc);
    }
    /**
     * 根据id查询商品描述
     * @param itemId
     * @return
     */
    @PostMapping("/selectItemDescByItemId")
    public TbItemDesc selectItemDescByItemId(Long itemId){
        return itemDescService.selectItemDescByItemId(itemId);
    }
}
