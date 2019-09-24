package com.backend.controller;

import com.backend.service.ContentCatgoryService;
import com.common.utils.Result;
import com.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内容类目controller
 */
@RestController
@RequestMapping("/content")
public class ContentCatgoryController {
    @Autowired
    private ContentCatgoryService contentCatgoryService;
    /**
     * 根据内容类目ID查询内容子类目
     * @param id
     * @return
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(defaultValue = "0")Long id){
        try {
            return contentCatgoryService.selectContentCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("erro");
    }
    /**
     * 添加内容类目
     * @param tbContentCategory
     * @return
     */
    @PostMapping("/insertContentCategory")
    public Result insertContentCategory(TbContentCategory tbContentCategory){
        try {
            return contentCatgoryService.insertContentCategory(tbContentCategory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("erro");
    }
    /**
     * 删除内容类目
     * @param categoryId
     * @return
     */
    @PostMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(Long categoryId){
        try {
            return contentCatgoryService.deleteContentCategoryById(categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
    /**
     * 修改内容类目
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(TbContentCategory tbContentCategory) {
        try {
            return contentCatgoryService.updateContentCategory(tbContentCategory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
