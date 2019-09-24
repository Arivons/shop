package com.backend.service;

import com.common.utils.Result;
import com.pojo.TbContent;

/**
 * 内容service
 */
public interface ContentService {
    /**
     * 根据分类id查询内容
     * @param categoryId
     * @return
     */
    Result selectTbContentAllByCategoryId(Long categoryId, Integer page, Integer rows);

    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    Result insertTbContent(TbContent tbContent);

    /**
     * 删除内容
     * @param ids
     * @return
     */
    Result deleteContentByIds(Long ids);
}
