package com.common.constants;

/**
 * 系统常量
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */


public class SystemConstants {


    /**
     * FileConstant
     *
     * @Author: WMING
     * @Date: 2023/07/21
     */
    public static class FileConstant {
        /**
         * jpg格式
         */
        public static final String JPG_EXTENSION = ".jpg";
        /**
         * png格式
         */
        public static final String PNG_EXTENSION = ".png";
    }

    /**
     * 请求头
     *
     * @Author: WMING
     * @Date: 2023/07/21
     */
    public static class HEADERS {
        /**
         * 博客客户端令牌
         */
        public static final String BLOG_TOKEN = "Blog-Token";
    }

    /**
     * 文章常量
     *
     * @Author: WMING
     * @Date: 2023/07/21
     */
    public static class ArticleConstant {
        /**
         * 文章状态草案
         */
        public static final int ARTICLE_STATUS_DRAFT = 1;
        /**
         * 文章状态正常
         */
        public static final int ARTICLE_STATUS_NORMAL = 0;

        /**
         * 分类状态草案
         */
        public static final int Category_STATUS_DRAFT = 0;

        /**
         * 类别状态正常
         */
        public static final int Category_STATUS_NORMAL = 0;
    }

}