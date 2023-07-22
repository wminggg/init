package com.gateway.controller.article;

import com.article.model.vo.ArticleCategoryVo;
import com.article.service.ArticleCategoryService;
import com.common.annotations.AuthIgnore;
import com.common.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章类别控制器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */


@RestController
@RequestMapping("/category")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    /**
     * 获取文章分类列表
     *
     * @return 响应结果
     */
    @AuthIgnore
    @GetMapping("/getCategoryList")
    public ResponseResult<List<ArticleCategoryVo>> getCategoryList() {
        List<ArticleCategoryVo> categoryList = articleCategoryService.getCategoryList();
        return ResponseResult.success(categoryList, "获取文章分类列表成功");
    }
}