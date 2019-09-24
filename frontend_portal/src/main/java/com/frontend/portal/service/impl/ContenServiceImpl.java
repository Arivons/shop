package com.frontend.portal.service.impl;

import com.common.utils.Result;
import com.frontend.portal.feign.CommonContentFeignClient;
import com.frontend.portal.feign.CommonRedisFeignClient;
import com.frontend.portal.service.ContenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContenServiceImpl implements ContenService {
    @Autowired
    private CommonContentFeignClient commonContentFeignClient;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    /**
     * 查询首页大广告
     * @return
     */
    @Override
    public Result selectFrontendContentByAD() {
        /**
         * 查询缓存
         */
        try {
            List<Map> maps = commonRedisFeignClient.selectContentAD();
            if(maps!=null&&maps.size()>0){
                return Result.ok(maps);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        /**
         * 查询数据库
         */
        List<Map> maps = this.commonContentFeignClient.selectFrontendContentByAD();
        /**
         * 加缓存
         */
        try {
            if(maps!=null&&maps.size()>0){
                commonRedisFeignClient.insertContentAD(maps);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

            if(maps!=null&&maps.size()>0){
                return Result.ok(maps);
            }
        return Result.error("暂无结果");
    }
}
