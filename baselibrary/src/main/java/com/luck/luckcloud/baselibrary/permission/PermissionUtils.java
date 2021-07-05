package com.luck.luckcloud.baselibrary.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * 权限请求工具类
 * Created by fa on 2019/5/13.
 */

public class PermissionUtils {
    private static final String TAG = PermissionUtils.class.getSimpleName();

    private PermissionUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 回调接口
     */
    public interface PermissionCallback {
        /**
         * 权限请求成功
         */
        void onRequestPermissionSuccess();

        /**
         * 用户拒绝了权限请求, 权限请求失败, 但还可以继续请求该权限
         *
         * @param permissions 请求失败的权限名
         */
        void onRequestPermissionFailure(List<String> permissions);

        /**
         * 用户拒绝了权限请求并且用户选择了以后不再询问, 权限请求失败, 这时将不能继续请求该权限, 需要提示用户进入设置页面打开该权限
         *
         * @param permissions 请求失败的权限名
         */
        void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions);
    }

    /**
     * 判断某些权限是否已经申请
     */
    public static boolean hasSelfPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public static void requestPermissions(FragmentActivity activity, final PermissionCallback permissionCallback, String... permissions) {
        if (permissions == null || permissions.length == 0) return;
        RxPermissions rxPermissions = new RxPermissions(activity);
        Disposable disposable = rxPermissions.requestEach(permissions)
                .buffer(permissions.length)
                .subscribe(new Consumer<List<Permission>>() {
                    @Override
                    public void accept(List<Permission> permissions) {
                        List<String> failurePermissions = new ArrayList<>();
                        List<String> askNeverAgainPermissions = new ArrayList<>();
                        for (Permission p : permissions) {
                            if (p.granted) {
                                continue;
                            }
                            if (p.shouldShowRequestPermissionRationale) {
                                failurePermissions.add(p.name);
                            } else {
                                askNeverAgainPermissions.add(p.name);
                            }
                        }
                        if (failurePermissions.size() == 0 && askNeverAgainPermissions.size() == 0) {
                            permissionCallback.onRequestPermissionSuccess();
                            return;
                        }
                        if (failurePermissions.size() > 0) {
                            permissionCallback.onRequestPermissionFailure(failurePermissions);
                        }
                        if (askNeverAgainPermissions.size() > 0) {
                            permissionCallback.onRequestPermissionFailureWithAskNeverAgain(askNeverAgainPermissions);
                        }
                    }
                });

    }


    /**
     * 请求摄像头权限
     */
    public static void launchCamera(FragmentActivity activity, PermissionCallback permissionCallback) {
        requestPermissions(activity, permissionCallback, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }


    /**
     * 请求外部存储的权限
     */
    public static void externalStorage(FragmentActivity activity, PermissionCallback permissionCallback) {
        requestPermissions(activity, permissionCallback, com.luck.luckcloud.baselibrary.permission.PermissionConfig.STORAGE);
    }


    /**
     * 请求发送短信权限
     */
    public static void sendSms(FragmentActivity activity, PermissionCallback permissionCallback) {
        requestPermissions(activity, permissionCallback, Manifest.permission.SEND_SMS);
    }


    /**
     * 请求打电话权限
     */
    public static void callPhone(FragmentActivity activity, PermissionCallback permissionCallback) {
        requestPermissions(activity, permissionCallback, Manifest.permission.CALL_PHONE);
    }


    /**
     * 请求获取手机状态的权限
     */
    public static void readPhoneState(FragmentActivity activity, PermissionCallback permissionCallback) {
        requestPermissions(activity, permissionCallback, Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * 请求定位权限
     */
    public static void location(FragmentActivity activity, PermissionCallback permissionCallback) {
        requestPermissions(activity, permissionCallback, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE);
    }
}
