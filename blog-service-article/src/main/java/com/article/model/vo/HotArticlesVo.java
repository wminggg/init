package com.article.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 热门文章vo
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticlesVo {
    /**
     * 文章ID;文章唯一标识
     */
    private Long articleId;

    /**
     * 标题;文章标题
     */
    private String title;

    /**
     * 作者;文章作者
     */
    private Long createBy;

    /**
     * 收藏量;文章用户收藏量
     */
    private Long favoriteCount;

    /**
     * 点赞量;文章用户点赞量
     */
    private Long likeCount;
}