package com.article.service;

import com.article.model.vo.ArticleDetailVo;
import com.article.model.vo.HotArticlesVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.article.model.entity.ArticleInfoEntity;
import com.common.model.vo.PageVo;

import java.util.List;


/**
 * 文章信息服务
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

public interface ArticleInfoService extends IService<ArticleInfoEntity> {


    /**
     * 热门文章列表
     *
     * @return
     */
    List<HotArticlesVo> getHotArticles();


    /**
     * 文章列表
     *
     * @param pageNum    页面num
     * @param pageSize   页面大小
     * @param categoryId 类别id
     * @return
     */
    PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId);


    /**
     * 获取文章内容
     *
     * @param id id
     * @return
     */
    ArticleDetailVo getArticleDetail(Long id);
}

