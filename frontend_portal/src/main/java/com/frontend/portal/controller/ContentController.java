package com.frontend.portal.controller;

import com.common.utils.Result;
import com.frontend.portal.service.ContenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/content")
public class ContentController {
    @Autowired
    private ContenService contenService;
    /**
     * 查询首页大广告
     * @return
     */
    @RequestMapping("/selectFrontendContentByAD")
    public Result selectFrontendContentByAD(){
        try {
            return contenService.selectFrontendContentByAD();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
