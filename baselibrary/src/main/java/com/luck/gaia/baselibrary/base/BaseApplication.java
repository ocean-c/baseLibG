package com.luck.gaia.baselibrary.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.luck.gaia.baselibrary.utils.LogUtils;

import java.util.List;

/**
 * author: fa
 * date: 2017/9/4 09:09.
 */

public abstract class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    public static Context applicationContext;
    public static boolean isBackground = false;// 应用是否在后台

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        // 由于友盟推送的服务会在独立进程中运行，所以这里需要判断调用onCreate的是不是当前主进程
        // 如果不是的话不需要重复调用onCreate方法
        // 判断是不是当前进程
        String processName = getProcessName(this, android.os.Process.myPid());
        LogUtils.e(TAG, "======processName=======>" + processName);
        beforeInit(processName);
        // 如果不是当前进程调用的就return，避免多次初始化
        if (processName == null || !processName.equalsIgnoreCase(getPackageName())) {
            return;
        }
        // 注册应用切到前台的监听，注意区分是否主进程
        listenForForeground();
        // 初始化方法
        init();
    }

    /**
     * 根据进程ID获取进程名字
     */
    public static String getProcessName(Context context, int pid) {
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (am == null) {
                return "";
            }
            List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
            if (runningApps == null) {
                return "";
            }
            for (ActivityManager.RunningAppProcessInfo processInfo : runningApps) {
                if (processInfo.pid == pid) {
                    return processInfo.processName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 初始化方法
     */
    protected abstract void init();

    /**
     * 带进程名字
     */
    protected void beforeInit(String processName) {

    }

    /**
     * 注册应用切到前台的监听
     */
    private void listenForForeground() {
        registerActivityLifecycleCallbacks(new DefaultActivityLifecycleCallbacks() {

            @Override
            public void onActivityResumed(Activity activity) {
                LogUtils.e(TAG, "================应用切换至前台======================>");
                isBackground = false;
            }
        });
    }

    /**
     * 应用切换至后台会回调
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            isBackground = true;
            LogUtils.e(TAG, "================应用切换至后台======================>");
        }
    }
}
