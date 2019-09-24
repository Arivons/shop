package com.backend.service.impl;

import com.backend.feign.CommonContentFeignClient;
import com.backend.service.ContentService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.utils.PageResult;
import com.common.utils.Result;
import com.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private CommonContentFeignClient commonContentFeignClient;
    /**
     * 根据分类id查询内容
     * @param categoryId
     * @return
     */
    @Override
    public Result selectTbContentAllByCategoryId(Long categoryId, Integer page, Integer rows) {
        PageResult pageResult = commonContentFeignClient.selectTbContentAllByCategoryId(categoryId, page, rows);
        if(pageResult!=null&pageResult.getResult()!=null&pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return Result.error("暂无结果");
    }

    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    @LcnTransaction
    @Override
    public Result insertTbContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        commonContentFeignClient.insertTbContent(tbContent);
        return Result.ok();
    }
    /**
     * 删除内容
     * @param ids
     * @return
     */
    @LcnTransaction
    @Override
    public Result deleteContentByIds(Long ids) {
        commonContentFeignClient.deleteContentByIds(ids);
        return Result.ok();
    }
}
