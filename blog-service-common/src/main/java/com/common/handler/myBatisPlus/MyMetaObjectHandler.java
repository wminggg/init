package com.common.handler.myBatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.common.utils.DateTimeUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * MyMetaObjectHandler
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            Long createTime = (Long) metaObject.getValue("createTime");
            Long updateTime = (Long) metaObject.getValue("updateTime");
            this.setFieldValByName("createTime", null == createTime ? DateTimeUtil.getStamp() : createTime, metaObject);
            this.setFieldValByName("updateTime", null == updateTime ? DateTimeUtil.getStamp() : updateTime, metaObject);
        } catch (ReflectionException e) {
            Long createdTime = (Long) metaObject.getValue("createdTime");
            Long updatedTime = (Long) metaObject.getValue("updatedTime");
            this.setFieldValByName("createdTime", null == createdTime ? DateTimeUtil.getStamp() : createdTime, metaObject);
            this.setFieldValByName("updatedTime", null == updatedTime ? DateTimeUtil.getStamp() : updatedTime, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String updateTime = metaObject.findProperty("updateTime", true);
        String updatedTime = metaObject.findProperty("updatedTime", true);

        if (Objects.nonNull(updateTime)){
            this.setFieldValByName("updateTime", DateTimeUtil.getStamp(), metaObject);
        }
        if (Objects.nonNull(updatedTime)){
            this.setFieldValByName("updatedTime", DateTimeUtil.getStamp(), metaObject);
        }
    }
}
