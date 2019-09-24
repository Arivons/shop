package com.common.redis.controller;

import com.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 首页大广告缓存Controller
 */
@RestController
@RequestMapping("/redis/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    /**
     * 插入首页大广告缓存
     * @param list
     */
    @RequestMapping("/insertContentAD")
    public void insertContentAD(@RequestBody List<Map> list){
        contentService.insertContentAD(list);
    }

    /**
     * 查询首页大广告缓存
     * @return
     */
    @RequestMapping("/selectContentAD")
    public List<Map> selectContentAD(){
        return contentService.selectContentAD();
    }
}
