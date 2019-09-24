package com.frontend.cart.feign;

import com.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("common-item")
public interface CommonItemFeignClient {

    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    @PostMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);
}
