package com.gateway.controller.common;

import com.common.model.ResponseResult;
import com.common.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;


    /**
     * 上传头像img
     *
     * @param img img
     * @return 响应结果
     */
    @PostMapping("/upload")
    public ResponseResult<Void> uploadImg(MultipartFile img){
        uploadService.uploadImg(img);
        return ResponseResult.success("上传头像img成功");
    }
}