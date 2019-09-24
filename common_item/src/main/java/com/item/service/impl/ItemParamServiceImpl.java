package com.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.item.service.ItemParamService;
import com.mapper.TbItemParamMapper;
import com.pojo.TbItemParam;
import com.pojo.TbItemParamExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格参数模板
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    /**
     * 查询商品类目模板
     * @param itemCatId
     * @return
     */
    @Override
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
        if(tbItemParams!=null&&tbItemParams.size()>0){
            return tbItemParams.get(0);
        }
        return null;
    }
    /**
     * 查询所有商品模板并分页
     * @return
     */
    @Override
    public PageResult selectItemParamAll(Integer page,Integer rows) {
        PageHelper.startPage(page, rows);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(null);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(tbItemParams);
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setTotalPage(pageInfo.getTotal());
        pageResult.setResult(tbItemParams);
        return pageResult;
    }
    /**
     * 添加商品规格参数模板
     * @param tbItemParam
     * @return
     */
    @LcnTransaction
    @Override
    public Integer insertItemParam(TbItemParam tbItemParam) {
        return tbItemParamMapper.insert(tbItemParam);
    }
    /**
     * 删除商品规格参数模板
     * @param id
     * @return
     */
    @LcnTransaction
    @Override
    public Integer deleteItemParamById(Long id) {
        return tbItemParamMapper.deleteByPrimaryKey(id);
    }
}
