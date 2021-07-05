package com.luck.luckcloud.baselibrary.http.callback;

import android.content.Context;

import com.luck.luckcloud.baselibrary.http.progress.entity.Progress;

import java.util.Map;

/**
 * 网络请求的回调
 * Created by 发 on 2017/5/31.
 */

public interface EngineCallback {

    // 执行之前会回调的方法
    void onPreExecute(Context context, Map<String, Object> params, Map<String, String> header);

    // 执行之后会回调的方法
    void onResponse(Context context, String token);

    void onCancel(Context context);

    void onError(Context context, String errorCode, String errorMsg);

    void onSuccess(Context context, String result);

    void onSuccess(Context context, byte[] result);

    void onProgressInThread(Context context, Progress progress);

    void onFileSuccess(Context context, String filePath);

    // 设置默认的CallBack
    EngineCallback DEFAULT_CALLBACK = new EngineCallback() {

        @Override
        public void onPreExecute(Context context, Map<String, Object> params, Map<String, String> header) {

        }

        @Override
        public void onResponse(Context context, String token) {

        }

        @Override
        public void onCancel(Context context) {

        }

        @Override
        public void onError(Context context, String errorCode, String errorMsg) {

        }

        @Override
        public void onSuccess(Context context, String result) {

        }

        @Override
        public void onSuccess(Context context, byte[] result) {
        }


        @Override
        public void onProgressInThread(Context context, Progress progress) {

        }

        @Override
        public void onFileSuccess(Context context, String result) {

        }
//
//        @Override
//        public ILoadingDialog getDialog() {
//            return null;
//        }

//        @Override
//        public boolean showLoadingDialog() {
//            return false;
//        }
//
//        @Override
//        public boolean loadingCancelable() {
//            return false;
//        }
    };
}
