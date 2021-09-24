package com.luck.gaia.framelibrary.entity;

import android.content.Context;

import com.luck.gaia.baselibrary.utils.StringUtils;
import com.luck.gaia.framelibrary.utils.PreferencesUtils;
//import com.yylearned.learner.thirdsupport.location.entity.LocationEntity;


/**
 * author: fa
 * date: 2017/11/16 11:55.
 */

public class User {
    private static final String TAG = User.class.getSimpleName();

    private User() {
    }

    private static User mInstance;

    public static User getInstance() {
        if (mInstance == null) {
            synchronized (User.class) {
                if (mInstance == null) {
                    mInstance = new User();
                }
            }
        }
        return mInstance;
    }

    // 用户Token
    private String token;
    // 用户是否登录
    private boolean isLogin;
    // 用户ID
    private String userId;
    // 用户手机号
    private String phone;
    // 真实姓名
    private String realName;
    // 昵称
    private String nickName;
    // 邀请码
    private String inviteCode;
    // 用户等级
    private int gradeNo;// 游客-黑旗 体验用户-粉旗 用户-红旗 初级-黄旗 中级-绿旗 高级-蓝旗 社区-紫旗
    // 用户身份
    private String identity;//身份 0 普通用户, 1 授权用户
    // 用户模式
    private String mode;//模式 0 普通版, 1 专家版, 2 基金版
    // 用户头像
    private String userHeader;
    // 推送相关的用户设备Token
    private String devicesToken;
    // 当前定位的经度
    private double longitude;
    // 当前定位的维度
    private double latitude;
    // 直播使用的userId 可能跟UserId一样，多歇一个字段避免不一样
    private String liveUserId;

    // 页面间传递JSon数据
    private String jsonStr;
    // 是否正在显示版本更新的Dialog
    private boolean isShowUpdateDialog = false;


    public String getToken(Context context) {
        token = PreferencesUtils.getTokenFromLocal(context);
        return token;
    }

    public void setToken(Context context, String token) {
        this.token = token;
        PreferencesUtils.saveTokenToLocal(context, token);
    }

    public boolean isLogin(Context context) {
        isLogin = PreferencesUtils.getIsLoginFromLocal(context);
        return isLogin && !StringUtils.isEmpty(getToken(context));
    }

    public void setLogin(Context context, boolean login) {
        isLogin = login;
        PreferencesUtils.saveIsLoginToLocal(context, login);
    }

    public String getPhone(Context context) {
        phone = PreferencesUtils.getMobileFromLocal(context);
        phone = StringUtils.isEmpty(phone) ? "" : phone;
        return phone;
    }

    public void setPhone(Context context, String phone) {
        this.phone = phone;
        PreferencesUtils.saveMobileToLocal(context, phone);
    }

    public String getUserId(Context context) {
        userId = PreferencesUtils.getUserIdFromLocal(context);
        return userId;
    }

    public void setUserId(Context context, String userId) {
        PreferencesUtils.saveUserIdToLocal(context, userId);
        this.userId = userId;
    }

    public String getUserHeader(Context context) {
        userHeader = PreferencesUtils.getHeaderFromLocal(context);
        return userHeader;
    }

    public void setUserHeader(Context context, String userHeader) {
        this.userHeader = userHeader;
        PreferencesUtils.saveHeaderToLocal(context, userHeader);
    }

    public String getDevicesToken() {
        return devicesToken;
    }

    public void setDevicesToken(String devicesToken) {
        this.devicesToken = devicesToken;
    }

    public String getRealName(Context context) {
        realName = PreferencesUtils.getUserRealName(context);
        return realName;
    }

    public void setRealName(Context context, String realName) {
        PreferencesUtils.saveUserRealName(context, realName);
        this.realName = realName;
    }

    public String getNickName(Context context) {
        nickName = PreferencesUtils.getNickNameFromLocal(context);
        return nickName;
    }

    public void setNickName(Context context, String nickName) {
        PreferencesUtils.saveNickNameToLocal(context, nickName);
        this.nickName = nickName;
    }

    public int getGradeNo(Context context) {
        gradeNo = PreferencesUtils.getUserGradeNo(context);
        return gradeNo;
    }

    public void setGradeNo(Context context, int gradeNo) {
        PreferencesUtils.saveUserGradeNo(context, gradeNo);
        this.gradeNo = gradeNo;
    }

    public String getInviteCode(Context context) {
        inviteCode = PreferencesUtils.getUserInviteCode(context);
        return inviteCode;
    }

    public void setInviteCode(Context context, String inviteCode) {
        PreferencesUtils.saveUserInviteCode(context, inviteCode);
        this.inviteCode = inviteCode;
    }

    public String getIdentity(Context context) {
        identity = PreferencesUtils.getUserIDEntity(context);
        return identity;
    }

    public void setIdentity(Context context, String identity) {
        PreferencesUtils.saveUserIDEntity(context, identity);
        this.identity = identity;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLiveUserId() {
        return liveUserId;
    }

    public void setLiveUserId(String liveUserId) {
        this.liveUserId = liveUserId;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public boolean isShowUpdateDialog() {
        return isShowUpdateDialog;
    }

    public void setShowUpdateDialog(boolean showUpdateDialog) {
        isShowUpdateDialog = showUpdateDialog;
    }

   /* public LocationEntity getLocationEntity() {
        return locationEntity;
    }

    public void setLocationEntity(LocationEntity locationEntity) {
        this.locationEntity = locationEntity;
    }
*/

    /**
     * 清空用户登录相关信息
     */
    public void clearUserLoginInfo(Context context) {
        // 清空用户的token
        setToken(context, "");
        // 登录状态
        setLogin(context, false);
        // 清空用户ID
        setUserId(context, "");
        // 清除用户头像
        setUserHeader(context, "");
        //用户等级
        User.getInstance().setGradeNo(context, 0);
        //用户邀请码
        User.getInstance().setInviteCode(context, "");
        //昵称
        User.getInstance().setNickName(context, "");
        //真实姓名
        User.getInstance().setRealName(context, "");
    }

    /**
     * 清除内存中的User信息
     */
    public void clear() {
        mInstance = null;
    }
}
