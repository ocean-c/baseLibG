package com.luck.gaia.framelibrary.utils;

import android.content.Context;

import com.luck.gaia.framelibrary.entity.User;
import com.luck.gaia.framelibrary.entity.UserInfoEntity;
import com.luck.gaia.framelibrary.http.HttpRequestUtils;
import com.luck.gaia.framelibrary.http.callback.DefaultHttpCallback;


/**
 * 用户信息的工具类
 * Created by fa on 2018/7/26.
 */

public class UserInfoUtils {
    private static final String TAG = UserInfoUtils.class.getSimpleName();

    /**
     * 接口获取用户信息
     */
    public void getUserInfo(Context context, String otherUserId, UserInfoCallback callback) {
        HttpRequestUtils.getUserInfo(context, otherUserId, new MyUserInfoRequestCallback(context, callback));
    }

    /**
     * 用户信息接口回调
     */
    private class MyUserInfoRequestCallback extends DefaultHttpCallback<UserInfoEntity> {
        private Context mContext;
        private UserInfoCallback mCallback;


        public MyUserInfoRequestCallback(Context context, UserInfoCallback callback) {
            this.mContext = context;
            this.mCallback = callback;
        }

        @Override
        protected void onResponseSuccess(UserInfoEntity result) {
            // 保存用户信息
            saveUserInfo(mContext, result);
            //
            if (mCallback != null) {
                mCallback.onUserInfoSuccess(result);
            }
        }

        @Override
        protected void onResponseFail(Context context, String errorCode, String errorMsg) {
            super.onResponseFail(context, errorCode, errorMsg);
            if (mCallback != null) {
                mCallback.onUserInfoFail(errorCode, errorMsg);
            }
        }

        @Override
        protected void onResponseError(Context context, String errorCode, String errorMsg) {
            super.onResponseError(context, errorCode, errorMsg);
            if (mCallback != null) {
                mCallback.onUserInfoFail(errorCode, errorMsg);
            }
        }
    }


    /**
     * 用户信息保存
     */
    public static void saveUserInfo(Context context, UserInfoEntity entity) {
        if (entity == null) {
            return;
        }
        // 登录和用户信息公用的同一个实体，但是用户信息接口不会返回Token，所以Token的存储不在这里做
        // 个人中心不返回UserId
        User.getInstance().setPhone(context, entity.getPhone());// 用户手机号本地化
        User.getInstance().setRealName(context, entity.getRealName());// 真实姓名
    }


    public interface UserInfoCallback {
        void onUserInfoSuccess(UserInfoEntity result);

        void onUserInfoFail(String code, String msg);
    }

}
