package com.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.item.service.ItemParamItemService;
import com.mapper.TbItemParamItemMapper;
import com.pojo.TbItemParamItem;
import com.pojo.TbItemParamItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    /**
     * 添加商品规格参数
     * @param tbItemParamItem
     * @return
     */
    @LcnTransaction
    @Override
    public Integer insertItemParamItem(TbItemParamItem tbItemParamItem) {

        return tbItemParamItemMapper.insert(tbItemParamItem);
    }
    /**
     * 跟新商品规格参数
     * @param tbItemParamItem
     * @return
     */
    @LcnTransaction
    @Override
    public Integer updateItemParamItem(TbItemParamItem tbItemParamItem) {
        TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(tbItemParamItem.getItemId());
        return tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem, tbItemParamItemExample);
    }
    /**
     * 根据id查询商品规格参数
     * @param itemId
     * @return
     */
    @Override
    public TbItemParamItem selectTbItemParamItemByItemId(Long itemId) {
        TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
        if(tbItemParamItems!=null&& tbItemParamItems.size()>0){
            return tbItemParamItems.get(0);
        }
        return null ;
    }
}
