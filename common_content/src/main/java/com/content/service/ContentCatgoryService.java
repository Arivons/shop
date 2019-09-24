package com.content.service;

import com.pojo.TbContentCategory;

import java.util.List;

/**
 * 内容类目Service
 */
public interface ContentCatgoryService {
    /**
     * 根据内容类目ID查询内容子类目
     * @param id
     * @return
     */
    List<TbContentCategory> selectContentCategoryByParentId(Long id);

    /**
     * 添加内容类目
     * @param tbContentCategory
     * @return
     */
    Integer insertContentCategory(TbContentCategory tbContentCategory);

    /**
     * 删除内容类目
     * @param categoryId
     * @return
     */
    Integer deleteContentCategoryById(Long categoryId);

    /**
     * 修改内容类目
     * @param tbContentCategory
     * @return
     */
    Integer updateContentCategory(TbContentCategory tbContentCategory);
}
