package com.article.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章列表vo
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {
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
}