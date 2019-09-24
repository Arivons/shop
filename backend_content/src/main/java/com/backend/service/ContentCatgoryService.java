package com.backend.service;

import com.common.utils.Result;
import com.pojo.TbContentCategory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ContentCatgoryService {
    /**
     * 根据内容类目ID查询内容子类目
     * @param id
     * @return
     */
    Result selectContentCategoryByParentId(@RequestParam("id") Long id);

    /**
     * 添加内容类目
     * @param tbContentCategory
     * @return
     */
    Result insertContentCategory(@RequestBody TbContentCategory tbContentCategory);

    /**
     * 删除内容类目
     * @param categoryId
     * @return
     */
    Result deleteContentCategoryById(@RequestParam("id")Long categoryId);

    /**
     * 修改内容类目
     * @param tbContentCategory
     * @return
     */
    Result updateContentCategory( TbContentCategory tbContentCategory);
}
