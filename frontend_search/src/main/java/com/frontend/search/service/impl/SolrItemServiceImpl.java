package com.frontend.search.service.impl;

import com.common.utils.Result;
import com.common.utils.SolrDocument;
import com.frontend.search.service.SolrItemService;
import com.github.pagehelper.PageHelper;
import com.mapper.SolrItemMapper;
import com.pojo.SolrItem;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolrItemServiceImpl implements SolrItemService {
    @Autowired
    private SolrTemplate solrTemplate;
    @Autowired
    private SolrItemMapper solrItemMapper;
    @Value("${spring.data.solr.core}")
    private String solrCore;
    /**
     * 导入数据到solr
     * @return
     */
    @Override
    public Result importSolrItemToSolr() {
        try {
            int i=1;
            while (true){
                PageHelper.startPage(i,1000);

                List<SolrItem> solrItems = solrItemMapper.findSolrItemAll();
                System.out.println(solrItems);
                if(solrItems==null||solrItems.size()<=0){
                    break;
                }
                List<SolrInputDocument> list = new ArrayList<>();

                for (SolrItem solrItem : solrItems) {
                    System.out.println(solrItem.getId());
                    SolrInputDocument solrInputDocument = new SolrInputDocument();
                    solrInputDocument.addField("id",solrItem.getId());
                    solrInputDocument.addField("item_title",solrItem.getTitle());
                    solrInputDocument.addField("item_price",solrItem.getPrice());
                    solrInputDocument.addField("item_image",solrItem.getImage());
                    solrInputDocument.addField("item_category_name",solrItem.getName());
                    solrInputDocument.addField("item_desc", solrItem.getItem_desc());
                    list.add(solrInputDocument);
                }
                solrTemplate.saveDocuments(solrCore, list);
                solrTemplate.commit(solrCore);
                i++;
            }
            return Result.ok("success");
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.error("error");
    }
    /**
     * 从solr检索数据
     * @param q
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<SolrDocument> selectByq(String q, Long page, Integer pageSize) {
        //设置高亮查询条件
        HighlightQuery query = new SimpleHighlightQuery();
        Criteria criteria = new Criteria("item_keywords");
        criteria.is(q);
        query.addCriteria(criteria);
        //设置高亮属性
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField("item_title");//设置高亮显示的域
        highlightOptions.setSimplePrefix("<em style='color:red'>");//设置高亮的样式的前缀
        highlightOptions.setSimplePostfix("</em>");
        query.setHighlightOptions(highlightOptions);
        //分页
        query.setOffset((page-1)*pageSize);
        query.setRows(pageSize);
        /**
         * 查询solr获取高亮信息
         */
        HighlightPage<SolrDocument> highlightPage = this.solrTemplate.queryForHighlightPage(this.solrCore,query,SolrDocument.class);
        List<HighlightEntry<SolrDocument>> highlighted = highlightPage.getHighlighted();
        for(HighlightEntry<SolrDocument>
                tbItemHighlightEntry:highlighted){
            SolrDocument entity = tbItemHighlightEntry.getEntity();//实体对象， 原始的实体对象
            List<HighlightEntry.Highlight> highlights = tbItemHighlightEntry.getHighlights();
        //如果有高亮， 就取高亮
            if(highlights != null && highlights.size() > 0 &&
                    highlights.get(0).getSnipplets().size() > 0){
                entity.setItem_title(highlights.get(0).getSnipplets().get(0));
            }
        }
        List<SolrDocument> list = highlightPage.getContent();
        return list;
    }
}
