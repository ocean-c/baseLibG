package com.luck.gaia.framelibrary.http.callback;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.luck.gaia.baselibrary.http.HttpUtils;
import com.luck.gaia.baselibrary.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回的data里面数据是Json数组的时候调用，T直接传实体就行，这里在外层已经套上了List
 * author: fa
 * date: 2017/8/21 16:11.
 */

public class DefaultHttpListCallback<T> extends com.luck.gaia.framelibrary.http.callback.DefaultHttpCallback<T> {
    private static final String TAG = DefaultHttpListCallback.class.getSimpleName();

    public DefaultHttpListCallback(Activity activity, boolean showLoadDialog) {
        super(activity, showLoadDialog);
    }

    public DefaultHttpListCallback(Fragment fragment, boolean showLoadDialog) {
        super(fragment, showLoadDialog);
    }

    public DefaultHttpListCallback() {
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void convertSuccessData(Gson gson, Object data) {
        final List<T> result = new ArrayList<>();
        try {
            // 当返回的数据是List类型的时候，这个Object就是ArrayList<Object>类型的；
            // 当集合中数据是对象的时候，这个Object是LinkedTreeMap，集合中是String的时候，Object就是String
            // 所以这里强转在数据没有问题的时候是不会出错的，不能用GSon转换，会把数字转成浮点
            final List<Object> temp = (List<Object>) data;
            if (temp != null) {
                Class<?> clazz = HttpUtils.analysisClassInfo(this);
                for (Object obj : temp) {
                    result.add((T) gson.fromJson(gson.toJson(obj), clazz));
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            LogUtils.e(TAG, "转换集合的时候异常，Message：" + e.getMessage() + "\n||Cause：" + e.getCause());
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onResponseSuccess(result);
            }
        });
        // 不在主线程的方法回调
        onResponseSuccessInThread(result);
    }

    /**
     * 涉及具体业务逻辑的返回
     *
     * @param result 返回的实体信息
     */
    protected void onResponseSuccess(List<T> result) {
    }

    /**
     * 涉及具体业务逻辑的返回(在子线程执行)
     *
     * @param result 返回的实体信息
     */
    protected void onResponseSuccessInThread(List<T> result) {
    }
}
