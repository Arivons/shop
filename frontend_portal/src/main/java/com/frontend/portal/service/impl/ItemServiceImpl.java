package com.frontend.portal.service.impl;

import com.common.utils.Result;
import com.frontend.portal.feign.CommonItemFeignClient;
import com.frontend.portal.feign.CommonRedisFeignClient;
import com.frontend.portal.service.ItemService;
import com.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    @Override
    public Result selectItemInfo(Long itemId) {
        /**
         * 查询缓存
         */
        try {
            TbItem tbItem = commonRedisFeignClient.findItemByItemId(itemId);
            if(tbItem!=null){
                return Result.ok(tbItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        TbItem tbItem = commonItemFeignClient.selectItemInfo(itemId);
        /**
         * 添加缓存
         */
        try {
            if(tbItem!=null){
                commonRedisFeignClient.insertItem(tbItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(tbItem!=null){
            return Result.ok(tbItem);
        }
        return Result.error("暂无结果");
    }
}
