package com.content.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.content.service.ContentCatgoryService;
import com.mapper.TbContentCategoryMapper;
import com.pojo.TbContentCategory;
import com.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    /**
     * 根据类目ID查询内容子类目
     * @param id
     * @return
     */
    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long id) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        return tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
    }
    /**
     * 添加内容类目
     * @param tbContentCategory
     * @return
     */
    @LcnTransaction
    @Override
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        /**
         * 补全内容类目数据并持久化到内容类目表
         */
        Date date = new Date();
        tbContentCategory.setIsParent(false);
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        int tbContentCategoryNum = tbContentCategoryMapper.insert(tbContentCategory);
        /**
         *判断父节点的节点状态,决定是否进行修改
         */
        TbContentCategory parentTbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        if(!parentTbContentCategory.getIsParent()){
            parentTbContentCategory.setUpdated(date);
            parentTbContentCategory.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(parentTbContentCategory);
        }
        return tbContentCategoryNum;
    }
    /**
     * 删除内容类目
     * @param categoryId
     * @return
     */
    @LcnTransaction
    @Override
    public Integer deleteContentCategoryById(Long categoryId) {
        /**
         * 删除当前节点及子节点
         */
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(categoryId);
        this.deleteNode(tbContentCategory);
        /**
         * 查询当前节点的兄弟节点数量
         */
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(tbContentCategory.getParentId());
        int  brotherNodeNum  = tbContentCategoryMapper.countByExample(tbContentCategoryExample);
        /**
         * 没有兄弟节点时修改父节点状态
         */
        if(brotherNodeNum==0){
            TbContentCategory parentTbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
            parentTbContentCategory.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKey(parentTbContentCategory);
        }
        return 200;
    }

    /**
     * 修改内容类目
     * @param tbContentCategory
     * @return
     */
    @LcnTransaction
    @Override
    public Integer updateContentCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }

    /**
     * 级联删除当前节点及子节点
     * @param tbContentCategory
     */
    @LcnTransaction
    public void deleteNode(TbContentCategory tbContentCategory){
        if(tbContentCategory.getIsParent()){
            TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
            criteria.andParentIdEqualTo(tbContentCategory.getId());
            List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
            for (TbContentCategory childrenContentCategory : tbContentCategories) {
                this.deleteNode(childrenContentCategory);
            }
            tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
        }else{
            tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
        }

    }
}
