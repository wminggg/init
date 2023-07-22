package com.article.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章详情vo
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    /**
     * 文章ID;文章唯一标识
     */
    private Long articleId;

    /**
     * 分类名称（非数据库字段）
     */
    private String categoryName;

    /**
     * 缩略图;缩略图
     */
    private String thumbnail;

    /**
     * 标题;文章标题
     */
    private String title;

    /**
     * 正文内容;文章正文内容
     */
    private String content;

    /**
     * 创建时间;文章创建时间
     */
    private Long createTime;

    /**
     * 点赞量;文章用户点赞量
     */
    private Long likeCount;

    /**
     * 收藏量;文章用户收藏量
     */
    private Long favoriteCount;

    /**
     * 作者;文章作者
     */
    private Long createBy;

    /**
     * 分类ID;关联文章分类信息表的分类ID字段
     */
    private Long categoryId;

    /**
     * 评论权限;是否允许评论 1是，0否
     */
    private String isComment;
}