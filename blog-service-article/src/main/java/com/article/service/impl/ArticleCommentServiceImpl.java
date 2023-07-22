package com.article.service.impl;

import com.article.mapper.ArticleCommentMapper;
import com.article.model.dto.ArticleCommentDto;
import com.article.model.entity.ArticleCommentEntity;
import com.article.model.vo.ArticleCommentVo;
import com.article.service.ArticleCommentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.constants.SystemConstants;
import com.common.model.vo.PageVo;
import com.common.utils.*;
import com.user.model.entity.UserInfoEntity;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 文章评论服务impl
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */
@Service("articleCommentService")
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleCommentEntity> implements ArticleCommentService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Override
    public PageVo commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 批量查询所有评论
        List<ArticleCommentEntity> allComments = baseMapper.getAllComments(articleId);

        if (allComments.isEmpty()) {
            // 返回空的评论列表
            return new PageVo(new ArrayList<>(), 0L);
        }

        // 分组父评论和子评论
        Map<Boolean, List<ArticleCommentEntity>> commentGroups = allComments.stream()
                .collect(Collectors.partitioningBy(comment -> comment.getRootId() == -1));

        List<ArticleCommentEntity> parentComments = commentGroups.get(true);
        List<ArticleCommentEntity> childComments = commentGroups.get(false);

        // 收集用户ID
        Set<Long> userIds = new HashSet<>();
        userIds.addAll(CollectionUtils.mapToList(parentComments, ArticleCommentEntity::getCreateBy));
        userIds.addAll(CollectionUtils.mapToList(childComments, ArticleCommentEntity::getCreateBy));
        userIds.addAll(CollectionUtils.mapToList(childComments, ArticleCommentEntity::getReplyUserId));

        // 批量查询用户昵称
        Map<Long, String> userNicknameMap = getUserNicknameMap(userIds);

        // 处理父评论（进行分页）
        List<ArticleCommentEntity> pagedParentComments = PageUtils.startPage(parentComments, pageNum, pageSize);

        // 构建评论Vo列表
        List<ArticleCommentVo> commentVoList = new ArrayList<>();

        for (ArticleCommentEntity parentComment : pagedParentComments) {
            ArticleCommentVo parentCommentVo = createArticleCommentVo(parentComment, userNicknameMap);
            commentVoList.add(parentCommentVo);

            // 处理与父评论关联的子评论（只显示前几条）
            List<ArticleCommentEntity> relatedChildComments = childComments.stream()
                    .filter(comment -> comment.getReplyCommentId().equals(parentComment.getCommentId()))
                    // 仅显示前3条子评论
                    .limit(3)
                    .collect(Collectors.toList());

            // 初始化子评论列表
            List<ArticleCommentVo> childCommentVoList = new ArrayList<>();

            for (ArticleCommentEntity childComment : relatedChildComments) {
                ArticleCommentVo childCommentVo = createArticleCommentVo(childComment, userNicknameMap);
                // 添加子评论到子评论列表
                childCommentVoList.add(childCommentVo);
            }

            // 将子评论列表设置到父评论Vo中
            parentCommentVo.setChildren(childCommentVoList);
        }

        // 获取总父评论数
        int totalParentComments = PageUtils.getTotal(parentComments);

        // 封装查询结果
        return new PageVo(commentVoList, (long) totalParentComments);
    }





    @Override
    public PageVo loadMoreChildrenComments(Long rootCommentId, Integer childPageNum, Integer childPageSize) {
        // 查询子评论
        Page<ArticleCommentEntity> childPage = new Page<>(childPageNum, childPageSize);
        List<ArticleCommentEntity> childrenComments = baseMapper.getChildrenComments(childPage, rootCommentId);

        if (childrenComments.isEmpty()) {
            // 返回空的评论列表
            return new PageVo(new ArrayList<>(), 0L);
        }

        // 收集用户ID
        Set<Long> userIds = childrenComments.stream()
                .flatMap(comment -> Stream.of(comment.getCreateBy(), comment.getReplyUserId()))
                .collect(Collectors.toSet());

        // 批量查询用户昵称
        Map<Long, String> userNicknameMap = getUserNicknameMap(userIds);

        // 构建子评论Vo列表
        List<ArticleCommentVo> childrenVoList =
                CollectionUtils.mapToList(childrenComments, comment -> createArticleCommentVo(comment, userNicknameMap));

        // 封装查询结果
        return new PageVo(childrenVoList, childPage.getTotal());
    }

    private Map<Long, String> getUserNicknameMap(Set<Long> userIds) {
        List<UserInfoEntity> userList = userService.listByIds(new ArrayList<>(userIds));
        return CollectionUtils.listToMap(userList, UserInfoEntity::getUserId, UserInfoEntity::getNickname);
    }

    private ArticleCommentVo createArticleCommentVo(ArticleCommentEntity comment, Map<Long, String> userNicknameMap) {
        ArticleCommentVo commentVo = BeanCopyUtils.copyBean(comment, ArticleCommentVo.class);
        commentVo.setNickname(userNicknameMap.get(comment.getCreateBy()));
        commentVo.setReplyCommentUserNickname(userNicknameMap.get(comment.getReplyUserId()));

        return commentVo;
    }




    @Override
    public void addComment(ArticleCommentDto commentDto) {
        // 创建 SnowFlakeUtils 实例
        SnowFlakeUtils snowFlakeUtils = SnowFlakeUtils.getInstance();
        ArticleCommentEntity comment = new ArticleCommentEntity();

        // 创建 ArticleCommentEntity 实例并设置属性值
        ObjectUtils.setIfNotNull(snowFlakeUtils.getNum(), comment::setCommentId);
        ObjectUtils.setIfNotNull(commentDto.getArticleId(), comment::setArticleId);
        ObjectUtils.setIfNotNull(commentDto.getRootId(), comment::setRootId);
        ObjectUtils.setIfNotNull(commentDto.getContent(), comment::setContent);
        ObjectUtils.setIfNotNull(commentDto.getReplyUserId(), comment::setReplyUserId);
        ObjectUtils.setIfNotNull(commentDto.getReplyCommentId(), comment::setReplyCommentId);

        //获取当前用户id
        String token = SystemConstants.HEADERS.BLOG_TOKEN;
        Long userId = TokenUtils.getUserId(request.getHeader(token));
        // 根据 userId 查询用户信息，设置 nickname、createBy、updateBy 等属性值
        UserInfoEntity userInfoEntity = userService.getById(userId);

        // 使用 setIfNotNull 工具类设置用户昵称
        ObjectUtils.setIfNotNull(userInfoEntity.getNickname(), comment::setNickname);

        // 执行插入评论数据的操作
        save(comment);
    }

}
