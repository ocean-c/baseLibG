package com.luck.gaia.framelibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.luck.gaia.baselibrary.utils.ActivityUtils;
import com.luck.gaia.baselibrary.utils.StringUtils;
import com.luck.gaia.baselibrary.utils.ToastUtils;
import com.luck.gaia.framelibrary.commom.PageRouteConfig;
import com.luck.gaia.framelibrary.entity.User;
import com.luck.gaia.framelibrary.entity.UserInfoEntity;
import com.luck.gaia.framelibrary.entity.event.LoginStateEvent;
import com.luck.gaia.framelibrary.http.HttpRequestUtils;
import com.luck.gaia.framelibrary.http.callback.DefaultHttpCallback;
import com.luck.gaia.framelibrary.view.dialog.WaitingDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * 登录的工具类
 * Created by fa on 2018/11/23.
 */

public class LoginUtils {
    private static final String TAG = LoginUtils.class.getSimpleName();
    // 登录等待对话框
    private WaitingDialog mWaitingDialog;
    //================================登录界面传递参数的Key====================================//
    // 登录之后跳转页面的Class全名
    public static final String KEY_PAGE_CLASS = "pageClassKey";
    // 登录成功之后是否需要关闭某个Activity，这里一般是上一个
    public static final String KEY_CLOSE_PAGE_ON_LOGIN_SUCCESS = "closePageKey";
    // 登录成功之后仅仅是关闭登录界面
    public static final String KEY_JUST_CLOSE_LOGIN_PAGE = "justCloseLoginPageKey";
    // 登录成功之后需要关闭的页面集合
    public static final String KEY_CLOSED_PAGE_LIST = "closePageListKey";

    public void login(final Context context, String name, String password, String code, String message, final LoginCallback callback) {
        HttpRequestUtils.login(context, name, password, code, message, new MyLoginRequestCallback(context, callback, true));
    }

    /**
     * 登录回调
     */
    private class MyLoginRequestCallback extends DefaultHttpCallback<UserInfoEntity> {
        private Context mContext;
        private LoginCallback mCallback;

        MyLoginRequestCallback(Context mContext, LoginCallback mCallback, boolean showDialog) {
            super((Activity) mContext, showDialog);
            dialogText("正在登录...");
            this.mContext = mContext;
            this.mCallback = mCallback;
        }

        @Override
        public void onResponse(Context context, String token) {
            // Token本地化
            User.getInstance().setToken(mContext, token);
        }

        @Override
        protected void onResponseSuccess(UserInfoEntity result) {
            onLoginSuccess(mContext, result);
            if (mCallback != null) {
                mCallback.onLoginSuccess(mContext);
            }
        }

        @Override
        protected void onResponseFail(Context context, String errorCode, String errorMsg) {
            super.onResponseFail(context, errorCode, errorMsg);
            User.getInstance().setToken(mContext, "");
            User.getInstance().setLogin(context, false);
            User.getInstance().setUserId(context, "");
            if (mCallback != null) {
                mCallback.onLoginFail(context, errorCode, errorMsg);
            }
        }

        @Override
        protected void onResponseError(Context context, String errorCode, String errorMsg) {
            super.onResponseError(context, errorCode, errorMsg);
            User.getInstance().setToken(mContext, "");
            User.getInstance().setLogin(context, false);
            User.getInstance().setUserId(context, "");
            if (mCallback != null) {
                mCallback.onLoginFail(context, errorCode, errorMsg);
            }
        }
    }

    /**
     * 登录成功的处理
     */
    public void onLoginSuccess(Context context, UserInfoEntity result) {

        ToastUtils.showToast(context, "登录成功");
        // 登录状态
        User.getInstance().setLogin(context, true);
        //用户id
        User.getInstance().setUserId(context, result.getUid());
        //用户等级
        User.getInstance().setGradeNo(context, result.getGradeNo());
        //用户邀请码
        User.getInstance().setInviteCode(context, result.getInviteCode());
        //昵称
        User.getInstance().setNickName(context, result.getUsername());
        //真实姓名
        User.getInstance().setRealName(context, result.getRealName());
        // 发通知更新登录状态
        EventBus.getDefault().post(new LoginStateEvent(true));
    }

    public void release() {
        if (mWaitingDialog != null) {
            mWaitingDialog.dismiss();
            mWaitingDialog = null;
        }
    }

    /**
     * 其他设备登录的处理
     */
    public void onLoginOtherDevices(Context context) {
        // 判断栈顶是不是登录的Activity
        String topActivityName = ActivityUtils.getTopActivitySimpleName();
        if (!StringUtils.isEmpty(topActivityName) && topActivityName.startsWith("Login")) {
            return;
        }
        // 获取该页面的Bundle
        Bundle bundle = ((Activity) context).getIntent().getExtras();
        if (bundle == null) {
            bundle = new Bundle();
        }
        // 关闭除主页外的所有页面
        ActivityUtils.getInstance().finishActivityExpect(PageRouteConfig.CLASS_MAIN_PAGE);
        // 获取页面的Class
        String clazz = ((Activity) context).getClass().getName();
        // 将class 放到Bundle中传到登录页面
        bundle.putString(KEY_PAGE_CLASS, clazz);
        // 打开登录界面
        switchActivity(PageRouteConfig.PAGE_LOGIN, bundle);
    }

    /**
     * 使用阿里ARouter进行页面跳转
     */
    protected void switchActivity(String router, Bundle bundle) {
        ARouter.getInstance().build(router).with(bundle).navigation();
    }

    /**
     * 跳转登录页，成功之后仅仅关闭登录页
     */
    public static void loginJustClosePage() {
        // 登录，成功之后仅仅关闭登录页面
        Bundle bundle = new Bundle();
        bundle.putBoolean(LoginUtils.KEY_JUST_CLOSE_LOGIN_PAGE, true);
        ARouter.getInstance().build(PageRouteConfig.PAGE_LOGIN).with(bundle).navigation();
    }

    /**
     * 登录回调
     */
    public interface LoginCallback {
        void onLoginSuccess(Context context);

        void onNeedArchived(Context context, UserInfoEntity entity);

        void onLoginFail(Context context, String code, String msg);
    }
}
