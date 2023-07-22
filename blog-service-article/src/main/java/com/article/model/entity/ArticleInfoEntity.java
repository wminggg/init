package com.article.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 文章信息实体
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Data
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@TableName("wm_article_info")
public class ArticleInfoEntity {
    /**
    * 文章ID;文章唯一标识
    */
    @TableId(value = "article_id", type = IdType.INPUT )
    private Long articleId;

    /**
    * 标签ID;外键，关联标签信息表的标签ID字段
    */
    private Long tagId;

    /**
    * 分类ID;外键，关联文章分类信息表的分类ID字段
    */
    private Long categoryId;

    /**
    * 置顶;是否置顶（0否，1是）
    */
    private String isTop;

    /**
    * 状态;状态（0已发布，1草稿）
    */
    private Integer status;

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
    * 评论权限;是否允许评论 1是，0否
    */
    private String isComment;

    /**
    * 可见范围;可见范围，0表示私密，1表示公开
    */
    private String visibility;

    /**
    * 用户ID;外键，关联用户信息表的用户ID字段
    */
    private Long userId;

    /**
    * 创建时间;文章创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    /**
    * 更新时间;文章更新时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
    * 删除标志;删除标志（0代表未删除，1代表已删除）
    */
    private Integer delFlag;

    /**
    * 点赞量;文章用户点赞量
    */
    private Long likeCount;

    /**
    * 收藏量;文章用户收藏量
    */
    private Long favoriteCount;

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

    /**
     * 类别名称
     */
    @TableField(exist = false)
    private String categoryName;

}
