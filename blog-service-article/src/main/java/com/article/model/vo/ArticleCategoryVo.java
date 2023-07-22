package com.article.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章类别vo
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategoryVo {
    /**
     * 分类ID;分类唯一标识
     */
    private Long categoryId;

    /**
     * 分类名称;文章所属分类的名称
     */
    private String categoryName;
}
