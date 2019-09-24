package com.frontend.portal.service.impl;

import com.common.utils.Result;
import com.frontend.portal.feign.CommonItemFeignClient;
import com.frontend.portal.feign.CommonRedisFeignClient;
import com.frontend.portal.service.ItemDescService;
import com.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    /**
     * 根据id查询商品描述
     * @param itemId
     * @return
     */
    @Override
    public Result selectItemDescByItemId(Long itemId) {
        /**
         * 查询缓存
         */
        try {
            TbItemDesc tbItemDesc = commonRedisFeignClient.findItemDescByItemId(itemId);
            if(tbItemDesc!=null){
                return Result.ok(tbItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        TbItemDesc tbItemDesc = commonItemFeignClient.selectItemDescByItemId(itemId);
        /**
         *      添加缓存
         */
        try {
            if(tbItemDesc!=null){
                commonRedisFeignClient.insertItemDesc(tbItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(tbItemDesc!=null){
            return Result.ok(tbItemDesc);
        }
        return Result.error("暂无结果");
    }
}
