package com.content.controller;

import com.common.utils.PageResult;
import com.content.service.ContentService;
import com.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    /**
     * 根据分类id查询内容
     * @param categoryId
     * @return
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(Long categoryId,Integer page,Integer rows){
        return contentService.selectTbContentAllByCategoryId(categoryId, page, rows);
    }
    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    @RequestMapping("/insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent){
        return contentService.insertTbContent(tbContent);
    }
    /**
     * 删除内容
     * @param ids
     * @return
     */
    @RequestMapping("/deleteContentByIds")
    public Integer deleteContentByIds(Long ids){
        return contentService.deleteContentByIds(ids);
    }

    /**
     * 查询首页大广告
     * @return
     */
    @RequestMapping("/selectFrontendContentByAD")
    public List<Map> selectFrontendContentByAD(){
        return  contentService.selectFrontendContentByAD();
    }
}
