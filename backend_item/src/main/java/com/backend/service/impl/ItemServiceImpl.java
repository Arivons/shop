package com.backend.service.impl;

import com.backend.feign.CommonItemFeignClient;
import com.backend.service.ItemService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.utils.IDUtils;
import com.common.utils.PageResult;
import com.common.utils.Result;
import com.pojo.TbItem;
import com.pojo.TbItemDesc;
import com.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    /*
     * 查询所有商品并分页
     */
    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {
        PageResult pageResult = commonItemFeignClient.selectTbItemAllByPage(page, rows);
        if (pageResult != null & pageResult.getResult() != null & pageResult.getResult().size() > 0) {
            return Result.ok(pageResult);
        }
        return Result.error("暂无结果");
    }

    /**
     * 添加商品
     *
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @LcnTransaction
    @Override
    public Result insertItem(TbItem tbItem, String desc, String itemParams) {
        long itemId = IDUtils.genItemId();
        Date date = new Date();
        /**
         * 补全商品信息
         */
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        /**
         * 补全商品描述信息
         */
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        /**
         * 补全商品规格参数信息
         */
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        /**
         * 持久化到数据库
         */
        Integer addItemNum = commonItemFeignClient.insertItem(tbItem);
        Integer addItemDescNum = commonItemFeignClient.insertItemDesc(tbItemDesc);
        Integer addItemParamItemNum = commonItemFeignClient.insertItemParamItem(tbItemParamItem);
        if (addItemNum == null || addItemDescNum == null || addItemParamItemNum == null) {
            throw new RuntimeException();
        }

        return Result.ok();
    }

    /**
     * 删除商品
     *
     * @param itemId
     * @return
     */
    @LcnTransaction
    @Override
    public Result deleteItemById(Long itemId) {

        TbItem tbItem = new TbItem();
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 3);
        Integer deleteItemNum = commonItemFeignClient.deleteItemById(tbItem);
        if (deleteItemNum == null) {
            throw new RuntimeException();
        }
        return Result.ok();

    }

    /**
     * 预更新商品查询
     *
     * @param itemId
     * @return
     */
    @Override
    public Result preUpdateItem(Long itemId) {
        Map<String, Object> map = commonItemFeignClient.preUpdateItem(itemId);
        if (map != null) {
            return Result.ok(map);
        }
        return Result.error("暂无结果");
    }

    /**
     * 跟新商品
     *
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @LcnTransaction
    @Override
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        Date date = new Date();
        /**
         * 跟新商品表
         */
        tbItem.setUpdated(date);
        Integer updateTbItemNum = commonItemFeignClient.updateTbItem(tbItem);
        /**
         * 跟新商品描述表
         */
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(date);
        Integer updateItemDescNum = commonItemFeignClient.updateItemDesc(tbItemDesc);
        /**
         * 跟新商品规格参数
         */
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setItemId(tbItem.getId());
        Integer updateItemParamItemNum = commonItemFeignClient.updateItemParamItem(tbItemParamItem);
        if(updateTbItemNum==null||updateItemDescNum==null||updateItemParamItemNum==null){
            throw new RuntimeException();
        }
        return Result.ok();
    }
}
