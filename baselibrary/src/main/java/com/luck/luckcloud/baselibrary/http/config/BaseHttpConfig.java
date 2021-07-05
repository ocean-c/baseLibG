package com.luck.luckcloud.baselibrary.http.config;

/**
 * Http参数配置
 * Created by fa on 2020/4/12.
 */
public class BaseHttpConfig {
    // 网络连接的超时时间（秒）
    public static final int CONNECT_TIMEOUT = 15;
    // 网络数据读取的超时时间（秒）
    public static final int READ_TIMEOUT = 100;
    // 网络数据写入的超时时间（秒）
    public static final int WRITE_TIMEOUT = 100;
    // 请求超时的提示错误码
    public static final String ERROR_CODE_BAD_REQUEST = "timeOut";
    // 网络无连接的错误码（本地使用）
    public static final String CODE_NETWORK_CONNECTIONLESS = "unConnect";
}
