package com.item.service.impl;

import com.common.utils.CatNode;
import com.common.utils.CatResult;
import com.item.service.ItemCatService;
import com.mapper.TbItemCatMapper;
import com.pojo.TbItemCat;
import com.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * 商品类目
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    /**
     * 根据id查询子节点
     * @param id
     * @return
     */
    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long id) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        criteria.andStatusEqualTo(1);
        return tbItemCatMapper.selectByExample(tbItemCatExample);
    }
    /**
     * 查询前台商品分类
     * @return
     */
    @Override
    public CatResult selectItemCategoryAll() {
        CatResult catResult = new CatResult();
        catResult.setData(getCatNodeAll((long) 0));
        return  catResult;
    }
    private List<?> getCatNodeAll(Long id){
        List catNodes = new ArrayList<>();
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
        for (TbItemCat tbItemCat : tbItemCats) {
            CatNode catNode = new CatNode();
            if(tbItemCat.getIsParent()){
                catNode.setName(tbItemCat.getName());
                catNode.setItem(getCatNodeAll(tbItemCat.getId()));
                catNodes.add(catNode);
            }else {
                catNodes.add(tbItemCat.getName());
            }
        }
        
        return catNodes;
    }
}
