package com.common.redis.controller;

import com.common.redis.service.ItemDescService;
import com.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/itemDesc")
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;
    /**
     * 添加商品描述缓存
     * @param tbItemDesc
     */
    @RequestMapping("/insertItemDesc")
    public void insertItemDesc(@RequestBody TbItemDesc tbItemDesc){
        itemDescService.insertItemDesc(tbItemDesc);
    }

    /**
     * 查询商品描述缓存
     * @param itemId
     * @return
     */
    @RequestMapping("/findItemDescByItemId")
    public TbItemDesc findItemDescByItemId(Long itemId){
        return itemDescService.findItemDescByItemId(itemId);
    }
}
