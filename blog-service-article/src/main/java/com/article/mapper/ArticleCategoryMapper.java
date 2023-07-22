package com.article.mapper;

import com.article.model.entity.ArticleCategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 文章类别映射器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Mapper
@Repository(value = "ArticleCategoryMapper")
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategoryEntity> {

}

