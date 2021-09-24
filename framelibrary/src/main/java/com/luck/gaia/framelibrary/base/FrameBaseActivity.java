package com.luck.gaia.framelibrary.base;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.luck.gaia.baselibrary.base.BaseActivity;
import com.luck.gaia.baselibrary.utils.AppInstallUtils;
import com.luck.gaia.baselibrary.utils.LogUtils;
import com.luck.gaia.baselibrary.utils.StringUtils;
import com.luck.gaia.framelibrary.R;
import com.luck.gaia.framelibrary.commom.Constants;
import com.luck.gaia.framelibrary.navigationBar.DefaultNavigationBar;
//import com.yylearned.learner.thirdsupport.umeng.UMUtils;


/**
 * 中间层Activity做业务扩展
 * Created by fa on 2017/5/31.
 */

public abstract class FrameBaseActivity extends BaseActivity {
    protected DefaultNavigationBar mTitleView;
    private static final String TAG = FrameBaseActivity.class.getSimpleName();

    @Override
    protected void initHeader() {
        if (titleId() != 0) {
            mTitleView = new DefaultNavigationBar.Builder(mContext)
                    .setTitle(titleId())
                    .setTitleBgColorRes(titleBgColor())
                    .setTitleTextColor(R.color.color_white)
                    .build();
        }
        if (!StringUtils.isEmpty(titleStr())) {
            mTitleView = new DefaultNavigationBar.Builder(mContext)
                    .setTitle(titleStr())
                    .setTitleBgColorRes(titleBgColor())
                    .setTitleTextColor(R.color.color_white)
                    .build();
        }
    }

    /**
     * 目前用来接收应用程序安装的情况
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppInstallUtils.REQUEST_CODE_UN_KNOW_SOURCE_INSTALL_APK) {
            // 手机未知来源安装权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                if (!hasInstallPermission) {
                    LogUtils.e(TAG, "用户拒绝，没有赋予未知来源应用安装权限");
                } else {
                    LogUtils.e(TAG, "用户赋予未知来源应用安装权限");
                    AppInstallUtils.getInstance().installAPKFile(mContext, Constants.FILE_CONTENT_FILE_PROVIDER);
                }
            }
        } else if (requestCode == AppInstallUtils.REQUEST_CODE_INSTALL_APK) {
            // 2018/8/2 下午4:31 在安装页面中退出安装了
            LogUtils.e(TAG, "用户取消APK安装");
        }
    }

    /**
     * 标题文字ID
     */
    protected int titleId() {
        return 0;
    }

    /**
     * 标题文字
     */
    protected String titleStr() {
        return "";
    }

    /**
     * 标题文字
     */
    protected int titleBgColor() {
        return R.color.color_111624;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 推送统计应用启动数据
//        UMUtils.with(this).onAppStart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (!containFragment()) {
            UMUtils.with(this).onPageStart(this.getClass().getName());
        }
        UMUtils.with(this).onResume();*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*if (!containFragment()) {
            UMUtils.with(this).onPageEnd(this.getClass().getName());
        }
        UMUtils.with(this).onPause();*/
    }

}
