package com.frontend.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient("common-content")
public interface CommonContentFeignClient {
    /**
     * 查询首页大广告
     * @return
     */
    @RequestMapping("/service/content/selectFrontendContentByAD")
    List<Map> selectFrontendContentByAD();
}
