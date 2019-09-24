package com.backend.service;

import com.common.utils.Result;
import com.pojo.TbItemParam;

/**
 * 商品规格参数模板Service
 */
public interface ItemParamService {
    /**
     * 查询商品类目模板
     * @param itemCatId
     * @return
     */
    Result selectItemParamByItemCatId( Long itemCatId);

    /**
     * 查询所有商品类目模板并分页
     * @return
     */
    Result selectItemParamAll(Integer page,Integer rows);

    /**
     * 添加商品模板
     * @param tbItemParam
     * @return
     */
    Result insertItemParam(TbItemParam tbItemParam);
    /**
     * 删除
     * @param id
     * @return
     */
    Result  deleteItemParamById(Long id);
}

