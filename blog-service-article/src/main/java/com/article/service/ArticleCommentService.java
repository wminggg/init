package com.article.service;

import com.article.model.dto.ArticleCommentDto;
import com.article.model.entity.ArticleCommentEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.common.model.vo.PageVo;


/**
 * 文章评论服务
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */
public interface ArticleCommentService extends IService<ArticleCommentEntity> {

    /**
     * 评论列表
     *
     * @param articleId 文章id
     * @param pageNum   页面num
     * @param pageSize  页面大小
     * @return 响应结果
     */
    PageVo commentList(Long articleId, Integer pageNum, Integer pageSize);

    /**
     * 加载更多孩子评论
     *
     * @param rootCommentId 根评论id
     * @param childPageNum  子页面num
     * @param childPageSize 子页面大小
     * @return 响应结果
     */
    PageVo loadMoreChildrenComments(Long rootCommentId, Integer childPageNum, Integer childPageSize);

    /**
     * 添加评论
     *
     * @param commentDto 评论dto
     * @return 响应结果
     */
    void addComment(ArticleCommentDto commentDto);

}
