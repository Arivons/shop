package com.content.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.common.utils.PageResult;
import com.content.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.TbContentMapper;
import com.pojo.TbContent;
import com.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;
    @Value("${frontend.portalAd}")
    private Long FRONTEND_PORTAL_AD;
    /**
     * 根据分类id查询内容
     * @param categoryId
     * @return
     */
    @Override
    public PageResult selectTbContentAllByCategoryId(Long categoryId,Integer page,Integer rows) {
        PageHelper.startPage(page, rows);
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setTotalPage(pageInfo.getTotal());
        pageResult.setResult(tbContents);
        return pageResult;
    }
    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    @LcnTransaction
    @Override
    public Integer insertTbContent(TbContent tbContent) {
        return tbContentMapper.insertSelective(tbContent);
    }
    /**
     * 删除内容
     * @param ids
     * @return
     */
    @LcnTransaction
    @Override
    public Integer deleteContentByIds(Long ids) {
        return tbContentMapper.deleteByPrimaryKey(ids);
    }
    /**
     * 查询首页大广告
     * @return
     */
    @Override
    public List<Map> selectFrontendContentByAD() {
        List list = new ArrayList();
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(FRONTEND_PORTAL_AD);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        for (TbContent tbContent : tbContents) {
            Map map = new HashMap();
            map.put("heightB", 200);
            map.put("src", tbContent.getPic());
            map.put("width", 670);
            map.put("alt", tbContent.getContent());
            map.put("srcB", null);
            map.put("widthB", 550);
            map.put("href", tbContent.getUrl());
            map.put("height", 240);
            list.add(map);
        }
        return list;
    }
}
