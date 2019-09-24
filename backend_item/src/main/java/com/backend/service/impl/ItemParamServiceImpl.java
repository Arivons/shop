package com.backend.service.impl;

import com.backend.feign.CommonItemFeignClient;
import com.backend.service.ItemParamService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.utils.PageResult;
import com.common.utils.Result;
import com.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    /**
     * 查询商品类目模板
     * @param itemCatId
     * @return
     */
    @Override
    public Result selectItemParamByItemCatId(Long itemCatId) {
        TbItemParam tbItemParam = commonItemFeignClient.selectItemParamByItemCatId(itemCatId);
        if(tbItemParam!=null){
            return Result.ok(tbItemParam);
        }
        return Result.error("暂无数据");
    }
    /**
     * 查询所有商品类目模板并分页
     * @return
     */
    @Override
    public Result selectItemParamAll(Integer page,Integer rows) {
        PageResult pageResult = commonItemFeignClient.selectItemParamAll(page, rows);
        if(pageResult!=null&pageResult.getResult()!=null&pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return Result.error("暂无结果");
    }

    /**
     * 添加商品模板
     * @param tbItemParam
     * @return
     */
    @LcnTransaction
    @Override
    public Result insertItemParam(TbItemParam tbItemParam) {
        try {
            commonItemFeignClient.insertItemParam(tbItemParam);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("添加失败");
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @LcnTransaction
    @Override
    public Result deleteItemParamById(Long id) {
        try {
            commonItemFeignClient.deleteItemParamById(id);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("删除失败");
    }
}
