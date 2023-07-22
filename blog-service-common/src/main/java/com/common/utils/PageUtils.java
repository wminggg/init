package com.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * PageUtils
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public class PageUtils {

    /**
     * 子列表
     *
     * @param list     列表
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @return 响应结果
     */
    public static <T> List<T> subList(List<T> list, int pageNum, int pageSize) {
        int totalSize = list.size();
        int fromIndex = (pageNum - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalSize);
        return list.subList(fromIndex, toIndex);
    }

    /**
     * 开始页面
     *
     * @param list     列表
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @return 响应结果
     */
    public static <T> List<T> startPage(List<T> list, int pageNum, int pageSize) {
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());
        if (startIndex >= endIndex) {
            return new ArrayList<>();
        }
        return list.subList(startIndex, endIndex);
    }

    /**
     * 得到总
     *
     * @param list 列表
     * @return 响应结果
     */
    public static <T> int getTotal(List<T> list) {
        return list.size();
    }
}
