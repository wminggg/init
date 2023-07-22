package com.article.mapper;

import com.article.model.entity.ArticleInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 条信息映射器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Mapper
@Repository(value = "ArticleInfoMapper")
public interface ArticleInfoMapper extends BaseMapper<ArticleInfoEntity> {

    /**
     * 选择热门文章
     *
     * @param page   页面
     * @param status 状态
     * @return 响应结果
     */
    List<ArticleInfoEntity> selectHotArticles(Page<ArticleInfoEntity> page, @Param("status") Integer status);


    /**
     * 选择文章列表
     *
     * @param page       页面
     * @param categoryId 类别id
     * @param status     状态
     * @return 响应结果
     */
    List<ArticleInfoEntity> selectArticleList(@Param("page") Page<ArticleInfoEntity> page, @Param("categoryId") Long categoryId, @Param("status") Integer status);


//    /**
//     * 多表联查：查询文章类别列表
//     *
//     * @param page       页面
//     * @param categoryId 类别id
//     * @param status     状态
//     * @return 响应结果
//     */
//    List<ArticleInfoEntity> selectArticleListWithCategory(Page<ArticleInfoEntity> page, @Param("categoryId") Long categoryId, @Param("status") Integer status);
}


