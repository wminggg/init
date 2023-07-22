package com.common.constants;

/**
 * RedisKeyConstant
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */
public class RedisKeyConstant {

    // 文章相关
    public static class Article {
        public static final String PREFIX = "article:";
        public static final String LIST = "article:list";
        public static final String VIEW_COUNT_PREFIX = "article:viewCount:";
    }

    // 用户相关
    public static class User {
        public static final String USER = "user:";

        /*客户端用户登录的token*/
        public static final String USER_TOKEN = "USER_TOKEN:";

    }

    // 点赞相关
    public static class Like {
        public static final String PREFIX = "like:";
        public static final String ARTICLE_PREFIX = "like:article:";
        public static final String USER_PREFIX = "like:user:";
    }

    // 评论相关
    public static class Comment {
        public static final String PREFIX = "comment:";
        public static final String ARTICLE_PREFIX = "comment:article:";
    }

    // 访问限制相关
    public static class RateLimit {
        public static final String PREFIX = "rateLimit:";
        public static final String IP_PREFIX = "rateLimit:ip:";
        public static final String USER_PREFIX = "rateLimit:user:";
    }

    // 缓存失效时间
    public static class Time {
        public static final Integer T_2 = 2;
        public static final Integer T_60 = 60;
        public static final Integer T_180 = 180;
        public static final Integer T_300 = 300;
        public static final Integer T_900 = 900;
        public static final Integer HOUR = 3600;                                                                    //一个小时
        public static final Integer DAY = 86400;                                                                    //一天
        public static final Integer WEEK = 1209600;                                                                 //14天
    }

    // 私有构造函数，防止实例化
    private RedisKeyConstant() {
    }
}
