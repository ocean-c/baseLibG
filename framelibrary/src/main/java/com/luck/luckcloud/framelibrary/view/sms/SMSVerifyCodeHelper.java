package com.luck.luckcloud.framelibrary.view.sms;

import android.app.Activity;
import android.content.Context;

import com.luck.luckcloud.framelibrary.http.HttpRequestUtils;
import com.luck.luckcloud.framelibrary.http.callback.DefaultHttpCallback;


/**
 * 接口发送短息验证码帮助类
 * author: fa
 * date: 2018/4/13 15:59.
 */

public class SMSVerifyCodeHelper extends com.luck.luckcloud.framelibrary.view.sms.BaseSMSVerifyCodeHelper {
    private static final String TAG = SMSVerifyCodeHelper.class.getSimpleName();

    public SMSVerifyCodeHelper(Context context) {
        super(context);
    }

    /**
     * @param phone     手机号
     * @param type      业务类型{@link SMSConfig}
     * @param imageCode 图形验证码
     * @param random    防刷随机数
     */
    @Override
    protected void request(String phone, String type, String imageCode, String random) {
        HttpRequestUtils.sendSMS(mContext, phone, type, new DefaultHttpCallback<Object>((Activity) mContext, true) {
            @Override
            protected void onResponseSuccess(Object result) {
                onSMSVerifySuccess();
            }

            @Override
            protected void onResponseFail(Context context, String errorCode, String errorMsg) {
                super.onResponseFail(context, errorCode, errorMsg);
                onSMSVerifyFail(errorCode);
            }

            @Override
            protected void onResponseError(Context context, String errorCode, String errorMsg) {
                super.onResponseError(context, errorCode, errorMsg);
                onSMSVerifyFail(errorCode);
            }
        });
    }
}
