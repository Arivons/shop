package com.content.service;

import com.common.utils.PageResult;
import com.pojo.TbContent;

import java.util.List;
import java.util.Map;

/**
 * 内容service
 */
public interface ContentService {
    /**
     * 根据分类id查询内容
     * @param categoryId
     * @return
     */
    PageResult selectTbContentAllByCategoryId(Long categoryId,Integer page,Integer rows);

    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    Integer insertTbContent(TbContent tbContent);

    /**
     * 删除内容
     * @param ids
     * @return
     */
    Integer deleteContentByIds(Long ids);

    /**
     * 查询首页大广告
     * @return
     */
    List<Map> selectFrontendContentByAD();
}
