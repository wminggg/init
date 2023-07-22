package com.common.annotations;

import java.lang.annotation.*;

/**
 * 认证忽略
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {
}
