package com.article.service.impl;

import com.article.model.entity.ArticleCategoryEntity;
import com.article.model.entity.ArticleInfoEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.article.mapper.ArticleInfoMapper;
import com.common.constants.SystemConstants;
import com.article.model.vo.ArticleDetailVo;
import com.article.model.vo.ArticleListVo;
import com.article.model.vo.HotArticlesVo;
import com.common.model.vo.PageVo;
import com.article.service.ArticleCategoryService;
import com.article.service.ArticleInfoService;

import com.common.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 文章信息服务impl
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Service("ArticleInfoService")
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoMapper, ArticleInfoEntity> implements ArticleInfoService {

    @Autowired
    private ArticleCategoryService categoryService;

    /**
     * 热门文章列表
     *
     * @return 响应结果
     */
    @Override
    public List<HotArticlesVo> getHotArticles() {
        // 分页对象
        Page<ArticleInfoEntity> page = new Page<>(1, 10);
        // 按照收藏量排序查询文章列表
        List<ArticleInfoEntity> articleInfoEntityList = baseMapper.selectHotArticles(page, SystemConstants.ArticleConstant.ARTICLE_STATUS_NORMAL);
        // 将文章列表转化为HotArticlesVo列表
        return BeanCopyUtils.copyBeanList(articleInfoEntityList, HotArticlesVo.class);
    }

    /**
     * 获取文章列表
     *
     * @param pageNum     当前页码
     * @param pageSize    每页数量
     * @param categoryId  分类ID
     * @return 响应结果
     */
    //方案一：多表查询
//    @Override
//    public PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId) {
//        // 分页查询
//        Page<ArticleInfoEntity> page = new Page<>(pageNum, pageSize);
//        List<ArticleInfoEntity> articles = baseMapper.selectArticleListWithCategory(page, categoryId, StatusEnum.PUBLISHED.getStatusCode());
//
//        // 封装查询结果
//        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
//
//        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
//        return pageVo;
//    }


    //方案2：批量查询
    @Override
    public PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 分页查询
        Page<ArticleInfoEntity> page = new Page<>(pageNum, pageSize);
        List<ArticleInfoEntity> articles = baseMapper.selectArticleList(page, categoryId, SystemConstants.ArticleConstant.ARTICLE_STATUS_NORMAL);

        // 批量查询分类信息
        List<Long> categoryIds = articles.stream().map(ArticleInfoEntity::getCategoryId).collect(Collectors.toList());
        Map<Long, ArticleCategoryEntity> categoryMap = categoryService.getCategoryMapByIds(categoryIds);

        // 设置分类名到文章对象中
        articles.forEach(article -> {
            ArticleCategoryEntity category = categoryMap.get(article.getCategoryId());
            if (category != null) {
                article.setCategoryName(category.getCategoryName());
            }
        });

        // 封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        return new PageVo(articleListVos, page.getTotal());
    }

    /**
     * 获取文章详情
     *
     * @param id 文章ID
     * @return 响应结果
     */
    @Override
    public ArticleDetailVo getArticleDetail(Long id) {
        // 根据ID查询文章
        ArticleInfoEntity article = getById(id);
        // 转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类ID查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        ArticleCategoryEntity category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getCategoryName());
        }
        // 封装响应返回
        return articleDetailVo;
    }

}
