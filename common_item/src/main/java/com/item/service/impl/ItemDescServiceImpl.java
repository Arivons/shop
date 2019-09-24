package com.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.item.service.ItemDescService;
import com.mapper.TbItemDescMapper;
import com.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    /**
     * 添加商品描述
     * @param tbItemDesc
     * @return
     */
   @LcnTransaction
    @Override
    public Integer insertItemDesc(TbItemDesc tbItemDesc) {

        return tbItemDescMapper.insert(tbItemDesc);
    }
    /**
     * 跟新商品描述
     * @param tbItemDesc
     * @return
     */
    @LcnTransaction
    @Override
    public Integer updateItemDesc(TbItemDesc tbItemDesc) {
        return tbItemDescMapper.updateByPrimaryKeyWithBLOBs(tbItemDesc);
    }
    /**
     * 根据id查询商品描述
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc selectItemDescByItemId(Long itemId) {
        return tbItemDescMapper.selectByPrimaryKey(itemId);
    }
}
