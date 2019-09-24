package com.item.service;

import com.common.utils.CatResult;
import com.pojo.TbItemCat;

import java.util.List;
/**
 * 商品类目
 */
public interface ItemCatService {
    /**
     * 根据id查询子节点
     * @param id
     * @return
     */
    List<TbItemCat> selectItemCategoryByParentId(Long id);

    /**
     * 查询前台商品分类
     * @return
     */
    CatResult selectItemCategoryAll();
}
