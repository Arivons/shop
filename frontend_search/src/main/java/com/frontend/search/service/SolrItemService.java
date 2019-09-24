package com.frontend.search.service;

import com.common.utils.Result;
import com.common.utils.SolrDocument;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * solrService
 */
public interface SolrItemService {
    /**
     * 导入数据到solr
     * @return
     */
    Result importSolrItemToSolr();

    /**
     * 从solr检索数据
     * @param q
     * @param page
     * @param pageSize
     * @return
     */
    List<SolrDocument> selectByq(String q, @RequestParam("1") Long page,@RequestParam("20") Integer pageSize);
}
