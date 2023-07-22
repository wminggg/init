package com.article.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 文章评论dto
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Data
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentDto {
    /**
     * 文章id;用于唯一标识每个评论的主键。
     */
    private Long articleId;

    /**
     * 根评论id;表示当前评论所属的根评论的ID。
     */
    private Long rootId;

    /**
     * 评论内容;存储实际的评论文本或内容。
     */
    @NotBlank(message = "评论内容不能为空")
    private String content;

    /**
     * 回复用户id;表示该评论是回复特定用户的。存储被回复用户的ID。
     */
    private Long replyUserId;

    /**
     * 回复评论id;表示该评论是回复特定评论的。存储被回复评论的ID。
     */
    private Long replyCommentId;
}