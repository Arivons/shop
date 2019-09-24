package com.backend.feign;

import com.common.utils.PageResult;
import com.pojo.TbContent;
import com.pojo.TbContentCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("common-content")
public interface CommonContentFeignClient {
    /**
     * 根据内容类目ID查询内容子类目
     * @param id
     * @return
     */
    @RequestMapping("/service/contentCatgory/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam("id") Long id);

    /**
     * 添加内容类目
     * @param tbContentCategory
     * @return
     */
    @PostMapping("/service/contentCatgory/insertContentCategory")
    Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory);

    /**
     * 删除内容类目
     * @param categoryId
     * @return
     */
    @PostMapping("/service/contentCatgory/deleteContentCategoryById")
    Integer deleteContentCategoryById(@RequestParam("categoryId")Long categoryId);
    /**
     * 修改内容类目
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("/service/contentCatgory/updateContentCategory")
    Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory);
    /**
     * 根据分类id查询内容
     * @param categoryId
     * @return
     */
    @RequestMapping("/service/content/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam("categoryId") Long categoryId,@RequestParam("page") Integer page,@RequestParam("rows") Integer rows);

    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    @RequestMapping("/service/content/insertTbContent")
    Integer insertTbContent(@RequestBody TbContent tbContent);

    /**
     * 删除内容
     * @param ids
     * @return
     */
    @RequestMapping("/service/content/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam("ids") Long ids);
}
