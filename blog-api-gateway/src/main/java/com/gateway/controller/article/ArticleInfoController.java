package com.gateway.controller.article;

import com.article.model.vo.ArticleDetailVo;
import com.article.model.vo.HotArticlesVo;
import com.article.service.ArticleInfoService;
import com.common.annotations.AuthIgnore;
import com.common.model.ResponseResult;
import com.common.model.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 条信息控制器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@RestController
@RequestMapping("/article")
public class ArticleInfoController {

    @Autowired
    private ArticleInfoService articleInfoService;

    /**
     * 获取热门文章列表
     *
     * @return 响应结果
     */
    @AuthIgnore
    @GetMapping("/getHotArticleList")
    public ResponseResult<List<HotArticlesVo>> hotArticleList() {
        List<HotArticlesVo> hotArticles = articleInfoService.getHotArticles();
        return ResponseResult.success(hotArticles, "获取热门文章列表成功");
    }


    /**
     * 获取文章列表
     *
     * @param pageNum    页面num
     * @param pageSize   页面大小
     * @param categoryId 类别id
     * @return 响应结果
     */
    @AuthIgnore
    @GetMapping("/getArticleList")
    public ResponseResult<PageVo> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        PageVo articleList = articleInfoService.articleList(pageNum, pageSize, categoryId);
        return ResponseResult.success(articleList, "获取文章列表成功");
    }


    /**
     * 获取文章内容
     *
     * @param articleId 文章id
     * @return 响应结果
     */
    @AuthIgnore
    @GetMapping("/getArticleDetail")
    public ResponseResult<ArticleDetailVo> getArticleDetail(Long articleId) {
        ArticleDetailVo articleDetailVo = articleInfoService.getArticleDetail(articleId);
        return ResponseResult.success(articleDetailVo, "获取文章内容成功");
    }
}