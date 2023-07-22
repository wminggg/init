package com.article.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 文字评论实体
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Data
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@TableName("wm_article_comment")
public class ArticleCommentEntity {
    /**
    * 评论id;用于唯一标识每个评论的主键。
    */
    @TableId(value = "comment_id", type = IdType.INPUT )
    private Long commentId;

    /**
    * 文章id;用于唯一标识每个评论的主键。
    */
    private Long articleId;

    /**
    * 用户id;表示发表评论的用户的ID。
    */
    @TableField(fill = FieldFill.INSERT)
    private Long userId;

    /**
    * 用户昵称;表示发表评论的用户昵称
    */
    private String nickname;

    /**
    * 根评论id;表示当前评论所属的根评论的ID。
    */
    private Long rootId;

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
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    /**
    * 评论更新时间;表示评论的最后更新时间戳或日期时间。
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
    * 删除标志;表示该评论是否被删除。0表示未删除，1表示已删除。
    */
    private Integer delFlag;

    /**
    * 创建者
    */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
    * 更新者
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

}
