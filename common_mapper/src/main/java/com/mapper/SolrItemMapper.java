package com.mapper;

import com.pojo.SolrItem;

import java.util.List;


public interface SolrItemMapper {
    /**
     * 查询所有
     * @return
     */
    List<SolrItem> findSolrItemAll();
}
