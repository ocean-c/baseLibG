package com.luck.luckcloud.framelibrary.commom;

import java.util.Arrays;

/**
 * 网络请求的状态码和常量
 * Created by fa on 2018/7/28.
 */

public class HttpConfig {

    public static final String DEVICES_TYPE_ANDROID = "1"; // 安卓1  IOS2

    // 接口成功返回的状态码
    public static final String CODE_REQUEST_SUCCESS = "SUCCESS";
    // 接口返回系统异常的状态码
    public static final String CODE_SYSTEM_ERROR = "SYSTEM_ERROR";
    // 接口返回验签失败的状态码
    public static final String CODE_SIGN_ERROR = "EXPIRED_TOKEN";
    // 接口返回验参失败的状态码
    public static final String CODE_PARAMS_ERROR = "INVALID_PARAMETER";
    //
    public static final String SERVICE_CODE_USER_NOT_LOGIN = "INVALID_TOKEN";// token无效
    public static final String SERVICE_CODE_TOKEN_OUT_DATE = "EXPIRED_TOKEN";// 验签失败
    public static final String SERVICE_CODE_TOKEN_AUTH_FAILD = "AUTH_FAILD";// 认证失败, 非法访问
    public static final String SERVICE_CODE_USER_ID_NULL = "1026";// 用户id不能为空
    public static final String SERVICE_CODE_TOKEN_OUT_DATE1 = "9802";// x-token不能为空
    // 版本更新状态码
    public static final String SERVICE_CODE_NEW_VERSION = "8000";


    // 其他设备登录错误码
    public static final String SERVICE_CODE_OTHER_DEVICE = "-802";
    // 账号封停错误码
    public static final String SERVICE_CODE_SEAL_UP = "-804";

    public static boolean isNeedLogin(String code) {
        return Arrays.asList(requestLoginCode).contains(code);
    }

    private static String[] requestLoginCode = new String[]{
            SERVICE_CODE_USER_NOT_LOGIN, SERVICE_CODE_TOKEN_OUT_DATE,
            SERVICE_CODE_USER_ID_NULL, SERVICE_CODE_TOKEN_OUT_DATE1,
            SERVICE_CODE_TOKEN_AUTH_FAILD
    };
}
