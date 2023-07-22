package com.article.service;

import com.article.model.vo.ArticleCategoryVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.article.model.entity.ArticleCategoryEntity;

import java.util.List;
import java.util.Map;


/**
 * 文章类别服务
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */
public interface ArticleCategoryService extends IService<ArticleCategoryEntity> {

    /**
     * 查询文章分类列表
     *
     * @return 响应结果
     */
    List<ArticleCategoryVo> getCategoryList();

    /**
     * 根据分类ID列表获取分类map
     *
     * @param categoryIds 分类ID列表
     * @return 分类ID与分类对象的映射
     */
    Map<Long, ArticleCategoryEntity> getCategoryMapByIds(List<Long> categoryIds);

}
