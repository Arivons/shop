package com.content.controller;

import com.content.service.ContentCatgoryService;
import com.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 内容类目Controller
 */
@RestController
@RequestMapping("/service/contentCatgory")
public class ContentCatgoryController {
    @Autowired
    private ContentCatgoryService contentCatgoryService;

    /**
     * 根据内容类目ID查询内容子类目
     * @param id
     * @return
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public List<TbContentCategory> selectContentCategoryByParentId(Long id){
        return contentCatgoryService.selectContentCategoryByParentId(id);
    }

    /**
     * 添加内容类目
     * @param tbContentCategory
     * @return
     */
    @PostMapping("/insertContentCategory")
    public Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return contentCatgoryService.insertContentCategory(tbContentCategory);
    }

    /**
     * 删除内容类目
     * @param categoryId
     * @return
     */
    @PostMapping("/deleteContentCategoryById")
    public Integer deleteContentCategoryById(Long categoryId){
        return contentCatgoryService.deleteContentCategoryById(categoryId);
    }
    /**
     * 修改内容类目
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("/updateContentCategory")
    public Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return contentCatgoryService.updateContentCategory(tbContentCategory);
    }

}
