package com.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.model.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 用户映射器
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Mapper
@Repository("UserMapper")
public interface UserMapper extends BaseMapper<UserInfoEntity> {

}

