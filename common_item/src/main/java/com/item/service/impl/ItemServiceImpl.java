package com.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.item.service.ItemService;
import com.mapper.TbItemCatMapper;
import com.mapper.TbItemDescMapper;
import com.mapper.TbItemMapper;
import com.mapper.TbItemParamItemMapper;
import com.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    /*
     * 查询所有商品并分页
     */
    @Override
    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andStatusEqualTo((byte) 1);
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItems);
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setTotalPage(pageInfo.getTotal());
        pageResult.setResult(tbItems);
        return pageResult;
    }

    /**
     * 添加商品
     * @param tbItem
     * @return
     */
    @LcnTransaction
    @Override
    public Integer insertItem(TbItem tbItem) {
        return tbItemMapper.insertSelective(tbItem);
    }
    /**
     * 删除或跟新商品
     * @param tbItem
     * @return
     */
    @LcnTransaction
    @Override
    public Integer updateItemById(TbItem tbItem) {
        return tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }
    /**
     * 预更新商品查询
     * @param itemId
     * @return
     */
    @Override
    public Map<String, Object> preUpdateItem(Long itemId) {
        Map<String, Object> map = new HashMap<>();
        /**
         * 查询商品
         */
        TbItem tbItem = this.tbItemMapper.selectByPrimaryKey(itemId);
        map.put("item", tbItem);
        /**
         * 查询商品类目
         */
        TbItemCat tbItemCat = this.tbItemCatMapper.selectByPrimaryKey(tbItem.getCid());
        map.put("itemCat", tbItemCat.getName());
        /**
         * 查询商品描述
         */
        TbItemDesc tbItemDesc = this.tbItemDescMapper.selectByPrimaryKey(itemId);
        map.put("itemDesc", tbItemDesc.getItemDesc());
        /**
         * 查询商品规格参数
         */
        TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = this.tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
        if(tbItemParamItems!=null){
            map.put("itemParamItem", tbItemParamItems.get(0).getParamData());
        }
        return map;
    }
    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    @Override
    public TbItem selectItemInfo(Long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }
}
