package com.article.service.impl;

import com.article.model.entity.ArticleCategoryEntity;
import com.article.model.entity.ArticleInfoEntity;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.article.mapper.ArticleCategoryMapper;
import com.common.constants.SystemConstants;
import com.article.model.vo.ArticleCategoryVo;
import com.article.service.ArticleCategoryService;
import com.article.service.ArticleInfoService;
import com.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文章类别服务impl
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Service("articleCategoryService")
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategoryEntity> implements ArticleCategoryService {

    @Autowired
    private ArticleInfoService articleInfoService;

    @Autowired
    private ArticleCategoryService categoryService;


    /**
     * 获取文章分类列表
     *
     * @return 响应结果
     */
    @Override
    public List<ArticleCategoryVo> getCategoryList() {
        // 查询文章表，状态为已发布的文章，并获取分类ID
        List<ArticleInfoEntity> publishedArticles = articleInfoService.list(Wrappers.<ArticleInfoEntity>lambdaQuery()
                .eq(ArticleInfoEntity::getStatus, SystemConstants.ArticleConstant.Category_STATUS_DRAFT)
                .select(ArticleInfoEntity::getCategoryId));

        // 获取已发布文章的分类ID集合（去重）
        Set<Long> categoryIds = publishedArticles.stream()
                .map(ArticleInfoEntity::getCategoryId)
                .collect(Collectors.toSet());

        // 查询已发布分类的分类名
        Map<Long, ArticleCategoryEntity> categoryMap = categoryService.getCategoryMapByIds(new ArrayList<>(categoryIds));

        // 封装为ArticleCategoryVo对象

        return CollectionUtils.mapToList(
                CollectionUtils.listToMap(categoryIds,categoryId -> categoryId,categoryMap::get).values(),
                category -> new ArticleCategoryVo(category.getCategoryId(), category.getCategoryName())
        );
    }


    /**
     * 查询分类id
     *
     * @param categoryIds 类别id
     * @return 响应结果
     */
    @Override
    public Map<Long, ArticleCategoryEntity> getCategoryMapByIds(List<Long> categoryIds) {
        if (categoryIds.isEmpty()) {
            return Collections.emptyMap();
        }
        List<ArticleCategoryEntity> categoryList = categoryService.list(Wrappers.<ArticleCategoryEntity>lambdaQuery()
                .in(ArticleCategoryEntity::getCategoryId, categoryIds)
                .eq(ArticleCategoryEntity::getStatus, SystemConstants.ArticleConstant.Category_STATUS_DRAFT));
        return CollectionUtils.listToMap(categoryList, ArticleCategoryEntity::getCategoryId, Function.identity());
    }

}
