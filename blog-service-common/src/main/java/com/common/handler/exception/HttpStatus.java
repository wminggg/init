//package com.common.handler.exception;
//
///**
// * HTTP状态码接口，包含了常见的HTTP状态码及其对应的整数值。
// */
//public interface HttpStatus {
//    // 1xx Informational
//    int SC_CONTINUE = 100; // 继续
//    int SC_SWITCHING_PROTOCOLS = 101; // 切换协议
//    int SC_PROCESSING = 102; // 处理中
//
//    // 2xx Success
//    int SC_OK = 200; // 请求成功
//    int SC_CREATED = 201; // 已创建
//    int SC_ACCEPTED = 202; // 已接受
//    int SC_NON_AUTHORITATIVE_INFORMATION = 203; // 非授权信息
//    int SC_NO_CONTENT = 204; // 无内容
//    int SC_RESET_CONTENT = 205; // 重置内容
//    int SC_PARTIAL_CONTENT = 206; // 部分内容
//    int SC_MULTI_STATUS = 207; // 多状态
//
//    // 3xx Redirection
//    int SC_MULTIPLE_CHOICES = 300; // 多种选择
//    int SC_MOVED_PERMANENTLY = 301; // 永久移动
//    int SC_MOVED_TEMPORARILY = 302; // 暂时移动
//    int SC_SEE_OTHER = 303; // 查看其他位置
//    int SC_NOT_MODIFIED = 304; // 未修改
//    int SC_USE_PROXY = 305; // 使用代理
//    int SC_TEMPORARY_REDIRECT = 307; // 临时重定向
//
//    // 4xx Client Error
//    int SC_BAD_REQUEST = 400; // 请求错误
//    int SC_UNAUTHORIZED = 401; // 未授权
//    int SC_PAYMENT_REQUIRED = 402; // 需要付款
//    int SC_FORBIDDEN = 403; // 禁止访问
//    int SC_NOT_FOUND = 404; // 未找到
//    int SC_METHOD_NOT_ALLOWED = 405; // 方法不允许
//    int SC_NOT_ACCEPTABLE = 406; // 不可接受
//    int SC_PROXY_AUTHENTICATION_REQUIRED = 407; // 需要代理身份验证
//    int SC_REQUEST_TIMEOUT = 408; // 请求超时
//    int SC_CONFLICT = 409; // 冲突
//    int SC_GONE = 410; // 已删除
//    int SC_LENGTH_REQUIRED = 411; // 需要内容长度
//    int SC_PRECONDITION_FAILED = 412; // 前提条件失败
//    int SC_REQUEST_TOO_LONG = 413; // 请求过长
//    int SC_REQUEST_URI_TOO_LONG = 414; // 请求的URI过长
//    int SC_UNSUPPORTED_MEDIA_TYPE = 415; // 不支持的媒体类型
//    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416; // 请求范围不符合要求
//    int SC_EXPECTATION_FAILED = 417; // 预期失败
//    int SC_INSUFFICIENT_SPACE_ON_RESOURCE = 419; // 资源空间不足
//    int SC_METHOD_FAILURE = 420; // 方法失败
//    int SC_UNPROCESSABLE_ENTITY = 422; // 不可处理的实体
//    int SC_LOCKED = 423; // 已锁定
//    int SC_FAILED_DEPENDENCY = 424; // 依赖失败
//    int SC_TOO_MANY_REQUESTS = 429; // 请求过多
//
//    // 5xx Server Error
//    int SC_INTERNAL_SERVER_ERROR = 500; // 服务器内部错误
//    int SC_NOT_IMPLEMENTED = 501; // 未实现
//    int SC_BAD_GATEWAY = 502; // 错误的网关
//    int SC_SERVICE_UNAVAILABLE = 503; // 服务不可用
//    int SC_GATEWAY_TIMEOUT = 504; // 网关超时
//    int SC_HTTP_VERSION_NOT_SUPPORTED = 505; // 不支持的HTTP版本
//    int SC_INSUFFICIENT_STORAGE = 507; // 存储空间不足
//}
