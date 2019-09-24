package com.frontend.search.controller;

import com.common.utils.Result;
import com.common.utils.SolrDocument;
import com.frontend.search.service.SolrItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ItemSolrController {
    @Autowired
    private SolrItemService solrItemService;
    @RequestMapping("/importAllItem")
    public Result importAllItem(){
        return solrItemService.importSolrItemToSolr();
    }

    /**
     * 从solr检索数据
     * @param q
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public List<SolrDocument> selectByq(String q, @RequestParam(defaultValue = "1") Long page,@RequestParam(defaultValue ="20") Integer pageSize){
        return solrItemService.selectByq(q, page, pageSize);
    }
}
