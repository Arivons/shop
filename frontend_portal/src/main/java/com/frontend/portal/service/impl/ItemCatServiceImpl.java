package com.frontend.portal.service.impl;

import com.common.utils.CatResult;
import com.common.utils.Result;
import com.frontend.portal.feign.CommonItemFeignClient;
import com.frontend.portal.feign.CommonRedisFeignClient;
import com.frontend.portal.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    /**
     * 查询前台商品分类
     * @return
     */
    @Override
    public Result selectItemCategoryAll() {
        /**
         * 查询缓存
         */
        try {
            CatResult catResult = commonRedisFeignClient.selectItemCategory();
            if(catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0){
                return Result.ok(catResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        CatResult catResult = commonItemFeignClient.selectItemCategoryAll();
        /**
         * 加缓存
         */
        try {
            if(catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0){
                commonRedisFeignClient.insertItemCategory(catResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0){
            return Result.ok(catResult);
        }

        return Result.error("暂无结果");
    }
}
