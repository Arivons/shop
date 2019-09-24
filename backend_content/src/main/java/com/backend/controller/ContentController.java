package com.backend.controller;

import com.backend.service.ContentService;
import com.common.utils.Result;
import com.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    /**
     * 根据分类id查询内容
     * @param categoryId
     * @return
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(Long categoryId, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer rows){
        try {
            return contentService.selectTbContentAllByCategoryId(categoryId, page, rows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent){
        try {
            return contentService.insertTbContent(tbContent);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids){
        try {
            return contentService.deleteContentByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }


}
