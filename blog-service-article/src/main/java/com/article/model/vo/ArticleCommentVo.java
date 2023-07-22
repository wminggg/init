package com.article.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文章评论vo
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentVo {
    /**
     * 评论id;用于唯一标识每个评论的主键。
     */
    private Long commentId;

    /**
     * 用户名;表示发表评论的用户的ID。
     */
    private Long userId;

    /**
     * 用户昵称;表示发表评论的用户昵称
     */
    private String nickname;

    /**
     * 评论内容;存储实际的评论文本或内容。
     */
    private String content;

    /**
     * 回复用户id;表示该评论是回复特定用户的。存储被回复用户的ID。
     */
    private Long replyUserId;

    /**
     * 回复评论id;表示该评论是回复特定评论的。存储被回复评论的ID。
     */
    private Long replyCommentId;

    /**
     * 根评论id;表示当前评论所属的根评论的ID。
     */
    private Long rootId;

    /**
     * 用户昵称;表示该评论是回复特定评论的。存储被回复评论的昵称。
     */
    private String replyCommentUserNickname;

    /**
     * 点赞量;表示该评论收到的点赞数量。
     */
    private Long likeCount;

    /**
     * dislike_count;表示该评论收到的踩的数量。
     */
    private Long dislikeCount;

    /**
     * 评论创建时间;表示评论的创建时间戳或日期时间。
     */
    private Long createTime;

    /**
     * 评论更新时间;表示评论的最后更新时间戳或日期时间。
     */
    private Long updateTime;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 孩子们
     */
    private List<ArticleCommentVo> children;
}