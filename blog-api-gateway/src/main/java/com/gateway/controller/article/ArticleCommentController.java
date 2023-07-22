package com.gateway.controller.article;


import com.article.model.dto.ArticleCommentDto;
import com.article.service.ArticleCommentService;
import com.common.annotations.AuthIgnore;
import com.common.model.ResponseResult;
import com.common.model.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章评论控制器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@RestController
@RequestMapping("/comment")
public class ArticleCommentController {
    
    @Autowired
    private ArticleCommentService commentService;

    /**
     * 获取评论列表
     *
     * @param articleId 文章id
     * @param pageNum   页面num
     * @param pageSize  页面大小
     * @return 响应结果
     */
    @AuthIgnore
    @GetMapping("/getCommentList")
    public ResponseResult<PageVo> commentList(Long articleId, Integer pageNum, Integer pageSize){
        PageVo commentList = commentService.commentList(articleId, pageNum, pageSize);
        return ResponseResult.success(commentList, "获取评论列表成功");
    }

    /**
     * 加载更多子评论
     *
     * @param rootCommentId 根评论id
     * @param childPageNum  子页面num
     * @param childPageSize 子页面大小
     * @return 响应结果
     */
    @AuthIgnore
    @GetMapping("/childrenCommentList")
    public ResponseResult<PageVo> loadMoreChildrenComments(Long rootCommentId, Integer childPageNum, Integer childPageSize) {
        PageVo childrenCommentList = commentService.loadMoreChildrenComments(rootCommentId, childPageNum, childPageSize);
        return ResponseResult.success(childrenCommentList, "加载更多子评论成功");
    }


    /**
     * 添加评论
     *
     * @param commentDto 评论dto
     * @return 响应结果
     */
    @PostMapping("/addComment")
    public ResponseResult<Void> addComment(@RequestBody ArticleCommentDto commentDto){
        commentService.addComment(commentDto);
        return ResponseResult.success("添加评论成功");
    }
}

