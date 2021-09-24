package com.luck.gaia.framelibrary.utils;

import android.content.Context;

import com.luck.gaia.baselibrary.http.config.BaseHttpConfig;
import com.luck.gaia.baselibrary.utils.StringUtils;
import com.luck.gaia.framelibrary.R;
import com.luck.gaia.framelibrary.commom.HttpConfig;

import java.util.HashMap;
import java.util.Map;


/**
 * 错误码和错误信息的工具类
 * Created by fa on 2017/6/19.
 */

public class ErrorCodeTips {
    private static Map<String, Integer> mErrorTipsMap = new HashMap<>();

    static {
        mErrorTipsMap.put(BaseHttpConfig.CODE_NETWORK_CONNECTIONLESS, R.string.msg_no_network);
        mErrorTipsMap.put(BaseHttpConfig.ERROR_CODE_BAD_REQUEST, R.string.msg_timeout);
        mErrorTipsMap.put(HttpConfig.CODE_SYSTEM_ERROR, R.string.msg_system_error);
        mErrorTipsMap.put(HttpConfig.CODE_SIGN_ERROR, R.string.msg_sign_error);
        mErrorTipsMap.put(HttpConfig.CODE_PARAMS_ERROR, R.string.msg_params_error);
    }

    public static int getErrorStringRes(String code) {
        if (StringUtils.isEmpty(code)) {
            return -1;
        }
        Integer StringRes = mErrorTipsMap.get(code);
        return StringRes != null ? StringRes : R.string.msg_timeout;
    }

    public static String getErrorString(Context context, String code) {
        int resId = getErrorStringRes(code);
        if (resId != -1) {
            return context.getResources().getString(resId);
        }
        return "";
    }

}
