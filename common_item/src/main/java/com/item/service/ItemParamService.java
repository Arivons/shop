package com.item.service;

import com.common.utils.PageResult;
import com.pojo.TbItemParam;

/**
 * 商品规格参数模板Service
 */
public interface ItemParamService {
    /**
     * 查询商品规格参数类目模板
     * @param itemCatId
     * @return
     */
    TbItemParam selectItemParamByItemCatId(Long itemCatId);
    /**
     * 查询所有商品规格参数模板并分页
     * @return
     */
    PageResult selectItemParamAll(Integer page,Integer rows);

    /**
     * 添加商品规格参数模板
     * @param tbItemParam
     * @return
     */
    Integer insertItemParam(TbItemParam tbItemParam);

    /**
     * 删除商品规格参数模板
     * @param id
     * @return
     */
    Integer deleteItemParamById(Long id);
}
