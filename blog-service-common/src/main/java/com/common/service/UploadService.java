package com.common.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传服务
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public interface UploadService {
    /**
     * 上传img
     *
     * @param img img
     * @return 响应结果
     */
    String uploadImg(MultipartFile img);
}