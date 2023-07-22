package com.article.mapper;

import com.article.model.entity.ArticleCommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 文章评论映射器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Mapper
@Repository(value = "ArticleCommentMapper")
public interface ArticleCommentMapper extends BaseMapper<ArticleCommentEntity> {

    /**
     * 查询所有评论
     *
     * @param articleId 文章id
     * @return 响应结果
     */
    List<ArticleCommentEntity> getAllComments(@Param("articleId") Long articleId);


    /**
     * 查询根评论
     *
     * @param page      页面
     * @param articleId 文章id
     * @return 响应结果
     */
    List<ArticleCommentEntity> getRootComments(@Param("page") Page<ArticleCommentEntity> page, @Param("articleId") Long articleId);

    /**
     * 查询子评论
     *
     * @param page   页面
     * @param rootId 根id
     * @return 响应结果
     */
    List<ArticleCommentEntity> getChildrenComments(@Param("page") Page<ArticleCommentEntity> page, @Param("rootId") Long rootId);

}


