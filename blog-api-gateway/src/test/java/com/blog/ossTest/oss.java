//package com.blog.ossTest;
//
//import com.google.gson.Gson;
//import com.qiniu.common.QiniuException;
//import com.qiniu.http.Response;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.Region;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.storage.model.DefaultPutRet;
//import com.qiniu.util.Auth;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.ByteArrayInputStream;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//
///**
// * @Author: WMING
// * @Date: 2023/5/21
// * @Description: TODO
// */
//
//@Component
//@SpringBootTest(classes = oss.class)
//@ConfigurationProperties(prefix = "oss")
//@EnableConfigurationProperties(oss.class)
//@RunWith(SpringRunner.class)
//@ConfigurationPropertiesScan(basePackages = "com.blog.ossTest")
//public class oss {
//
//    private String accessKey;
//    private String secretKey;
//    private String bucket;
//
//    public String getAccessKey() {
//        return accessKey;
//    }
//
//    public void setAccessKey(String accessKey) {
//        this.accessKey = accessKey;
//    }
//
//    public String getSecretKey() {
//        return secretKey;
//    }
//
//    public void setSecretKey(String secretKey) {
//        this.secretKey = secretKey;
//    }
//
//    public String getBucket() {
//        return bucket;
//    }
//
//    public void setBucket(String bucket) {
//        this.bucket = bucket;
//    }
//
//    @Test
//    public void testOss() {
//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Region.autoRegion());
//        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//        //...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
//        //...生成上传凭证，然后准备上传
////        String accessKey = "1Mbm7sgngjzJ5Cy-djLwrD-_L5vkPv9UKpIhlxhP";
////        String secretKey = "563GStuKJqKise0bWIL2yqMydeNqldfczAo7_gcC";
////        String bucket = "wming-blog";
//
//        //默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = "wm-jpg";
//
//        try {
////            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
////            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
//
//
//            InputStream inputStream = new FileInputStream("C:\\Users\\MING\\Desktop\\1.jpg");
//            Auth auth = Auth.create(accessKey, secretKey);
//            String upToken = auth.uploadToken(bucket);
//
//            try {
//                Response response = uploadManager.put(inputStream,key,upToken,null, null);
//                //解析上传成功的结果
//                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
//            } catch (QiniuException ex) {
//                Response r = ex.response;
//                System.err.println(r.toString());
//                try {
//                    System.err.println(r.bodyString());
//                } catch (QiniuException ex2) {
//                    //ignore
//                }
//            }
//        } catch (Exception e) {
//            //ignore
//        }
//    }
//}