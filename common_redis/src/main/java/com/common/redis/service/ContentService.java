package com.common.redis.service;

import java.util.List;
import java.util.Map;

/**
 * 内容缓存Service
 */
public interface ContentService {
    /**
     * 插入首页大广告缓存
     * @param list
     */
    void insertContentAD(List<Map> list);

    /**
     * 查询首页大广告缓存
     * @return
     */
    List<Map> selectContentAD();

}
