package com.article.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 文章类别实体
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Data
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@TableName("wm_article_category")
public class ArticleCategoryEntity {
    /**
    * 分类ID;分类唯一标识
    */
    @TableId(value = "category_id", type = IdType.INPUT )
    private Long categoryId;

    /**
    * 分类名称;文章所属分类的名称
    */
    private String categoryName;

    /**
    * 父分类;父分类id，如果没有父分类为-1
    */
    private Long parentId;

    /**
    * 分类描述;文章所属分类的描述信息
    */
    private String description;

    /**
    * 状态;状态0:正常,1禁用
    */
    private Integer status;

    /**
    * 创建时间;分类创建的时间
    */
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    /**
    * 更新时间;分类更新的时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
    * 删除标志;删除标志（0代表未删除，1代表已删除）
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
