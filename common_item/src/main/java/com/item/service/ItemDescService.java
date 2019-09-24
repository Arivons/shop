package com.item.service;

import com.pojo.TbItemDesc;
import org.springframework.stereotype.Service;

/**
 * 商品描述Service
 */
@Service
public interface ItemDescService {
    /**
     * 添加商品描述
     * @param tbItemDesc
     * @return
     */
    Integer insertItemDesc(TbItemDesc tbItemDesc);

    /**
     * 跟新商品描述
     * @param tbItemDesc
     * @return
     */
    Integer updateItemDesc(TbItemDesc tbItemDesc);

    /**
     * 根据id查询商品描述
     * @param itemId
     * @return
     */
    TbItemDesc selectItemDescByItemId(Long itemId);
}
