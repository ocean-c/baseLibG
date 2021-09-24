package com.luck.gaia.baselibrary.utils;

import android.content.Context;

import com.luck.gaia.baselibrary.toast.CustomToast;


/**
 * Toast显示的工具类
 * author: fa
 * date: 2017/12/21 15:34.
 */

public class ToastUtils {
    private static final String TAG = ToastUtils.class.getSimpleName();

    public static void showToast(Context context, CharSequence msg) {
        CustomToast.showCenterShortToast(context, msg, 0);
    }

    public static void showToast(Context context, int res) {
        CustomToast.showCenterShortToast(context, context.getString(res), 0);
    }

    public static void showLongToast(Context context, CharSequence msg) {
        CustomToast.showCenterLongToast(context, msg, 0);
    }

    /**
     * 取消Toast
     */
    public static void cancelToast() {
        CustomToast.cancelToast();
    }
}
