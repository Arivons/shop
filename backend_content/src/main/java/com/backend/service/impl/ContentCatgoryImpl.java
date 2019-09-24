package com.backend.service.impl;

import com.backend.feign.CommonContentFeignClient;
import com.backend.service.ContentCatgoryService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.utils.Result;
import com.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCatgoryImpl implements ContentCatgoryService {
    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    /**
     * 根据内容类目ID查询内容子类目
     *
     * @param id
     * @return
     */
    @Override
    public Result selectContentCategoryByParentId( Long id) {
        List<TbContentCategory> tbContentCategories = commonContentFeignClient.selectContentCategoryByParentId(id);
        if (tbContentCategories != null && tbContentCategories.size() > 0) {
            return Result.ok(tbContentCategories);
        }
        return Result.error("暂无结果");
    }

    /**
     * 添加内容类目
     *
     * @param tbContentCategory
     * @return
     */
    @LcnTransaction
    @Override
    public Result insertContentCategory(TbContentCategory tbContentCategory) {
        commonContentFeignClient.insertContentCategory(tbContentCategory);
        return Result.ok();
    }

    /**
     * 删除内容类目
     *
     * @param categoryId
     * @return
     */
    @LcnTransaction
    @Override
    public Result deleteContentCategoryById(Long categoryId) {
        commonContentFeignClient.deleteContentCategoryById(categoryId);
        return Result.ok();
    }
    /**
     * 修改内容类目
     * @param tbContentCategory
     * @return
     */
    @Override
    public Result updateContentCategory(TbContentCategory tbContentCategory) {
        commonContentFeignClient.updateContentCategory(tbContentCategory);
        return Result.ok();
    }
}
