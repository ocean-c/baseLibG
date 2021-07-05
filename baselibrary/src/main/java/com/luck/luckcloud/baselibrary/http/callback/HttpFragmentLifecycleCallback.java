package com.luck.luckcloud.baselibrary.http.callback;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.luck.luckcloud.baselibrary.http.dialog.LoadingDialog;
import com.luck.luckcloud.baselibrary.utils.LogUtils;


/**
 * 网络请求使用的Fragment生命周期回调
 * Created by fa on 2019/6/18.
 */

public class HttpFragmentLifecycleCallback extends FragmentManager.FragmentLifecycleCallbacks {
    private static final String TAG = HttpFragmentLifecycleCallback.class.getSimpleName();

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment fragment) {
        LogUtils.e(TAG, "Fragment======》" + fragment.getClass().getSimpleName() + "     销毁");
        // Fragment销毁的时候在这里统一销毁LoadingDialog
        LoadingDialog.getInstance().onFragmentDestroy(fragment);
    }
}
