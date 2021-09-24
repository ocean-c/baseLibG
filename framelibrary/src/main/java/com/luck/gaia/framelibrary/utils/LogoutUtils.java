package com.luck.gaia.framelibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.luck.gaia.baselibrary.utils.LogUtils;
import com.luck.gaia.framelibrary.entity.User;
import com.luck.gaia.framelibrary.entity.event.LoginStateEvent;
import com.luck.gaia.framelibrary.http.HttpRequestUtils;
import com.luck.gaia.framelibrary.http.callback.DefaultHttpCallback;

import org.greenrobot.eventbus.EventBus;

/**
 * 退出登录的工具类
 * author: fa
 * date: 2017/8/16 11:30.
 */
public class LogoutUtils {
    private static final String TAG = LogoutUtils.class.getSimpleName();

    public void logout(final Context context, boolean showDialog, final LogoutCallback callback) {
        try {
            if (context instanceof Activity) {
                HttpRequestUtils.logout(context, new DefaultHttpCallback<Object>((Activity) context, showDialog) {
                    @Override
                    protected void onResponseSuccess(Object result) {
                        onLogoutSuccess(context, callback);
                    }
                });
            } else {
                HttpRequestUtils.logout(context, new DefaultHttpCallback<Object>() {
                    @Override
                    protected void onResponseSuccess(Object result) {
                        onLogoutSuccess(context, callback);
                    }
                });
            }
            onLogoutSuccess(context, callback);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(TAG, "退出登录异常" + Log.getStackTraceString(e));
        }
    }

    private void onLogoutSuccess(final Context context, final LogoutCallback callback) {
        // 清空用户登录信息
        User.getInstance().clearUserLoginInfo(context);
        // 发通知
        EventBus.getDefault().post(new LoginStateEvent(false));
        //
        if (callback != null) {
            callback.onLogout(context);
        }
    }

    /**
     * 退出应用
     */
    public void exitApplication(Context context) {
        // 清空内存中的User实体
        User.getInstance().clear();
    }

    public interface LogoutCallback {
        void onLogout(Context context);
    }
}
