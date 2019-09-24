package com.backend.service.impl;

import com.backend.feign.CommonItemFeignClient;
import com.backend.service.ItemCatService;
import com.common.utils.Result;
import com.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    /**
     * 根据id查询子节点
     * @param id
     * @return
     */
    @Override
    public Result selectItemCategoryByParentId(Long id) {
        List<TbItemCat> tbItemCats = commonItemFeignClient.selectItemCategoryByParentId(id);
        if (tbItemCats!=null&&tbItemCats.size()>0){
            return Result.ok(tbItemCats);
        }
        return Result.error("暂无数据");
    }


}
