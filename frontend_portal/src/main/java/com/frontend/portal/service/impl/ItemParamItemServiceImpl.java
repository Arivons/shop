package com.frontend.portal.service.impl;

import com.common.utils.Result;
import com.frontend.portal.feign.CommonItemFeignClient;
import com.frontend.portal.feign.CommonRedisFeignClient;
import com.frontend.portal.service.ItemParamItemService;
import com.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    /**
     * 根据id查询商品规格参数
     * @param itemId
     * @return
     */
    @Override
    public Result selectTbItemParamItemByItemId(Long itemId) {
        /**
         * 查询缓存
         */
        try {
            TbItemParamItem tbItemParamItem = commonRedisFeignClient.findTbItemParamItemByItemId(itemId);
            if(tbItemParamItem!=null){
                return Result.ok(tbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        TbItemParamItem tbItemParamItem = commonItemFeignClient.selectTbItemParamItemByItemId(itemId);
        /**
         * 添加缓存
         */
        try {
            if(tbItemParamItem!=null){
                commonRedisFeignClient.insertItemParamItem(tbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(tbItemParamItem!=null){
            return Result.ok(tbItemParamItem);
        }
        return Result.error("暂无结果");
    }
}
