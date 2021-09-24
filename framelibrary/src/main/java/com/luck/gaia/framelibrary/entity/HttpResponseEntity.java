package com.luck.gaia.framelibrary.entity;

/**
 * 接口数据返回整体解析的实体类
 * author: fa
 * date: 2017/8/21 16:46.
 */

public class HttpResponseEntity {
    // 接口状态码
    private String code;
    // 数据
    private Object data;
    // 状态描述
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResult() {
        return data;
    }

    public void setResult(Object result) {
        this.data = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", info='" + message + '\'' +
                '}';
    }
}
