package com.luck.gaia.framelibrary.utils;

import android.content.Context;

import com.luck.gaia.baselibrary.utils.BasePreferencesUtils;
import com.luck.gaia.baselibrary.utils.StringUtils;


public class PreferencesUtils extends BasePreferencesUtils {
    private static final String TAG = PreferencesUtils.class.getSimpleName();

    // sp文件的名字
    private static final String SETTINGS = "settings";

    // 保存相关信息的KEY
    private static final String KEY_UPDATE_APK_SIZE = "downloadApkSizeKey";//存储更新使用的APK文件的大小的KEY
    private static final String KEY_TOKEN = "tokenKey";// 保存用户Token的Key
    private static final String KEY_IS_LOGIN = "isLoginKey";// 保存用户是否登录的Key
    private static final String KEY_MOBILE = "mobileKey";// 保存用户手机号的Key
    private static final String KEY_CONTACT_MOBILE = "contactMobileKey";// 保存用户手机号的Key
    private static final String KEY_ID = "userIdKey";// 保存用户ID的Key
    private static final String KEY_NICK_NAME = "nickNameKey";// 保存用户昵称的Key
    private static final String KEY_USER_HEADER = "userHeaderKey";// 保存用户头像的Key
    private static final String KEY_USER_INVITE_CODE = "userInviteCode";// 保存用户邀请码
    private static final String KEY_USER_GRADENO = "userGradeNo";// 保存用户等级
    private static final String KEY_USER_IDENTITY = "userIdentity";// 保存用户身份
    private static final String KEY_USER_REAL_NAME = "userRealName";// 保存用户实名的Key
    private static final String KEY_USER_HAS_PSW = "hasPswKey";// 保存用户实名的Key
    private static final String KEY_VERSION_CODE = "versionCodeKey";// 保存版本信息的Key
    private static final String KEY_CHANNEL_TYPE = "channelTypeKey";// 保存用户类型的Key
    private static final String KEY_SHOW_GUIDE_PAGE = "showGuidePageKey";// 保存引导页是否显示的Key
    private static final String KEY_USER_IM_ACCOUNT = "userIMAccount";// 保存用户IM账号的Key
    private static final String KEY_USER_IM_PASSWORD = "userIMPassword";// 保存用户IM密码的Key
    private static final String KEY_BROKER_CHAT_ID = "brokerChatId";// 保存用户对应经纪人ID的Key
    private static final String KEY_BROKER_HEAD = "brokerHead";// 保存用户对应经纪人头像的Key
    private static final String KEY_BROKER_NICK_NAME = "brokerNickname";// 保存用户对应经纪人昵称的Key
    private static final String KEY_CURRENT_CITY_ID = "currentCityId1";// 保存当前定位、选择的城市的Id（加了地图了，得改名字，否则老数据没有经纬度）
    private static final String KEY_CURRENT_CITY_NAME = "currentCityName1";// 保存当前定位、选择的城市的名字（加了地图了，得改名字，否则老数据没有经纬度）
    private static final String KEY_CURRENT_CITY_LNG = "currentCityLng";// 保存当前选择的城市经度
    private static final String KEY_CURRENT_CITY_LAT = "currentCityLat";// 保存当前选择的城市纬度

    private static final String KEY_USER_LANGUAGE = "language";// 保存当前选择的语言
//    private static final String KEY_SHOW_START_AD = "showStartADPageKey";// 是否显示启动广告页

    /**
     * 获取本地存储的用户账号
     */
    public static String getTokenFromLocal(Context context) {
        return getString(SETTINGS, context, KEY_TOKEN, "");
    }

    /**
     * 用户账号存储到本地
     */
    public static void saveTokenToLocal(Context context, String value) {
        putString(SETTINGS, context, KEY_TOKEN, value);
    }

    /**
     * 本地获取用户是否登录
     */
    public static boolean getIsLoginFromLocal(Context context) {
        return getBoolean(SETTINGS, context, KEY_IS_LOGIN);
    }

    /**
     * 用户是否登录存储到本地
     */
    public static void saveIsLoginToLocal(Context context, boolean value) {
        putBoolean(SETTINGS, context, KEY_IS_LOGIN, value);
    }

    /**
     * 获取本地存储的用户手机号
     */
    public static String getMobileFromLocal(Context context) {
        return getString(SETTINGS, context, KEY_MOBILE);
    }

    /**
     * 用户手机号存储到本地
     */
    public static void saveMobileToLocal(Context context, String value) {
        putString(SETTINGS, context, KEY_MOBILE, value);
    }

    /**
     * 获取本地存储的用户联系手机号
     */
    public static String getContactMobileFromLocal(Context context) {
        return getString(SETTINGS, context, KEY_CONTACT_MOBILE);
    }

    /**
     * 用户联系手机号存储到本地
     */
    public static void saveContactMobileToLocal(Context context, String value) {
        putString(SETTINGS, context, KEY_CONTACT_MOBILE, value);
    }

    /**
     * 获取本地存储的用户ID
     */
    public static String getUserIdFromLocal(Context context) {
        return getString(SETTINGS, context, KEY_ID, "");
    }

    /**
     * 用户ID存储到本地
     */
    public static void saveUserIdToLocal(Context context, String value) {
        putString(SETTINGS, context, KEY_ID, value);
    }

    /**
     * 获取本地存储的用户昵称
     */
    public static String getNickNameFromLocal(Context context) {
        return getString(SETTINGS, context, KEY_NICK_NAME);
    }

    /**
     * 用户昵称存储到本地
     */
    public static void saveNickNameToLocal(Context context, String value) {
        putString(SETTINGS, context, KEY_NICK_NAME, value);
    }


    /**
     * 获取本地存储的用户头像
     */
    public static String getHeaderFromLocal(Context context) {
        return getString(SETTINGS, context, KEY_USER_HEADER);
    }

    /**
     * 用户头像存储到本地
     */
    public static void saveHeaderToLocal(Context context, String value) {
        putString(SETTINGS, context, KEY_USER_HEADER, value);
    }

    /**
     * 获取本地存储的用户实名
     */
    public static String getUserRealName(Context context) {
        return getString(SETTINGS, context, KEY_USER_REAL_NAME);
    }

    /**
     * 用户实名存储到本地
     */
    public static void saveUserRealName(Context context, String value) {
        putString(SETTINGS, context, KEY_USER_REAL_NAME, value);
    }

    /**
     * 获取本地存储的用户是否设置密码
     */
    public static boolean getUserHasPsw(Context context) {
        return getBoolean(SETTINGS, context, KEY_USER_HAS_PSW);
    }

    /**
     * 用户是否设置密码存储到本地
     */
    public static void saveUserHasPsw(Context context, boolean value) {
        putBoolean(SETTINGS, context, KEY_USER_HAS_PSW, value);
    }

    /**
     * 获取本地存储的用户类型
     */
    public static String getChannelType(Context context) {
        return getString(SETTINGS, context, KEY_CHANNEL_TYPE);
    }

    /**
     * 用户类型存储到本地
     */
    public static void saveChannelType(Context context, String value) {
        putString(SETTINGS, context, KEY_CHANNEL_TYPE, value);
    }


    /**
     * 获取本地存储的是否显示了引导页
     */
    public static boolean getShowGuidePage(Context context) {
        return getBoolean(SETTINGS, context, KEY_SHOW_GUIDE_PAGE);
    }

    /**
     * 是否显示了引导页存储到本地
     */
    public static void saveShowGuidePage(Context context, boolean value) {
        putBoolean(SETTINGS, context, KEY_SHOW_GUIDE_PAGE, value);
    }

    //********************************************************//

    /**
     * 版本信息本地化
     */
    public static void saveVersionToLocal(Context context, int value) {
        putInt(SETTINGS, context, KEY_VERSION_CODE, value);
    }

    /**
     * 获取本地保存的版本信息
     */
    public static int getVersionFromLocal(Context context) {
        return getInt(SETTINGS, context, KEY_VERSION_CODE, 0);
    }

    /**
     * APK文件大小信息本地化
     */
    public static void saveUpdateAPKSizeToLocal(Context context, long value) {
        putLong(SETTINGS, context, KEY_UPDATE_APK_SIZE, value);
    }

    /**
     * 获取本地保存的更新使用的APK文件大小
     */
    public static long getUpdateAPKSizeFromLocal(Context context) {
        return getLong(SETTINGS, context, KEY_UPDATE_APK_SIZE, 0L);
    }

    /**
     * 获取本地存储的用户IM账号
     */
    public static String getIMAccount(Context context) {
        return getString(SETTINGS, context, KEY_USER_IM_ACCOUNT);
    }

    /**
     * 用户IM账号保存本地
     */
    public static void setIMAccount(Context context, String value) {
        putString(SETTINGS, context, KEY_USER_IM_ACCOUNT, value);
    }

    /**
     * 获取本地存储的用户IM密码
     */
    public static String getIMPassword(Context context) {
        return getString(SETTINGS, context, KEY_USER_IM_PASSWORD);
    }

    /**
     * 用户IM密码保存本地
     */
    public static void setIMPassword(Context context, String value) {
        putString(SETTINGS, context, KEY_USER_IM_PASSWORD, value);
    }

    /**
     * 获取本地存储的用户对应经纪人ChatId
     */
    public static String getBrokerChatId(Context context) {
        return getString(SETTINGS, context, KEY_BROKER_CHAT_ID);
    }

    /**
     * 用户对应经纪人ChatID保存本地
     */
    public static void setBrokerChatId(Context context, String value) {
        putString(SETTINGS, context, KEY_BROKER_CHAT_ID, value);
    }

    /**
     * 获取本地存储的用户对应经纪人头像
     */
    public static String getBrokerHead(Context context) {
        return getString(SETTINGS, context, KEY_BROKER_HEAD);
    }

    /**
     * 用户对应经纪人头像保存本地
     */
    public static void setBrokerHead(Context context, String value) {
        putString(SETTINGS, context, KEY_BROKER_HEAD, value);
    }

    /**
     * 获取本地存储的用户对应经纪人昵称
     */
    public static String getBrokerName(Context context) {
        return getString(SETTINGS, context, KEY_BROKER_NICK_NAME);
    }

    /**
     * 用户对应经纪人昵称保存本地
     */
    public static void setBrokerName(Context context, String value) {
        putString(SETTINGS, context, KEY_BROKER_NICK_NAME, value);
    }


    /**
     * 获取本地存储的当前定位、选择的城市ID
     */
    public static String getCurrentCityId(Context context) {
        return getString(SETTINGS, context, KEY_CURRENT_CITY_ID);
    }

    /**
     * 当前定位、选择的城市ID保存本地
     */
    public static void setCurrentCityId(Context context, String value) {
        putString(SETTINGS, context, KEY_CURRENT_CITY_ID, value);
    }

    /**
     * 获取本地存储的当前定位、选择的城市名字
     */
    public static String getCurrentCityName(Context context) {
        return getString(SETTINGS, context, KEY_CURRENT_CITY_NAME);
    }

    /**
     * 当前定位、选择的城市名字保存本地
     */
    public static void setCurrentCityName(Context context, String value) {
        putString(SETTINGS, context, KEY_CURRENT_CITY_NAME, value);
    }

    /**
     * 获取本地存储的当前选择的城市经度
     */
    public static double getCurrentCityLng(Context context) {
        return getFloat(SETTINGS, context, KEY_CURRENT_CITY_LNG);
    }

    /**
     * 当前当前选择的城市经度保存本地
     */
    public static void setCurrentCityLng(Context context, double value) {
        putFloat(SETTINGS, context, KEY_CURRENT_CITY_LNG, (float) value);
    }

    /**
     * 获取本地存储的当前选择的城市纬度
     */
    public static double getCurrentCityLat(Context context) {
        return getFloat(SETTINGS, context, KEY_CURRENT_CITY_LAT);
    }

    /**
     * 当前当前选择的城市纬度保存本地
     */
    public static void setCurrentCityLat(Context context, double value) {
        putFloat(SETTINGS, context, KEY_CURRENT_CITY_LAT, (float) value);
    }

    /**
     * 获取用户邀请码
     *
     * @param context
     * @return
     */
    public static String getUserInviteCode(Context context) {
        return getString(SETTINGS, context, KEY_USER_INVITE_CODE);
    }

    /**
     * 保存用户邀请码
     *
     * @param context
     * @param gradeNo
     */
    public static void saveUserInviteCode(Context context, String gradeNo) {
        putString(SETTINGS, context, KEY_USER_INVITE_CODE, gradeNo);
    }

    /**
     * 获取用户等级
     *
     * @param context
     * @return
     */
    public static int getUserGradeNo(Context context) {
        return getInt(SETTINGS, context, KEY_USER_GRADENO, 1);
    }

    /**
     * 保存用户等级
     *
     * @param context
     * @param gradeNo
     */
    public static void saveUserGradeNo(Context context, int gradeNo) {
        putInt(SETTINGS, context, KEY_USER_GRADENO, gradeNo);
    }

    /**
     * 获取用户身份
     *
     * @param context
     * @return
     */
    public static String getUserIDEntity(Context context) {
        return getString(SETTINGS, context, KEY_USER_IDENTITY);
    }

    /**
     * 保存用户身份
     *
     * @param context
     * @param gradeNo
     */
    public static void saveUserIDEntity(Context context, String gradeNo) {
        putString(SETTINGS, context, KEY_USER_IDENTITY, gradeNo);
    }

    /**
     * 获取语言
     *
     * @param context
     */
    public static String getUserLanguage(Context context) {
        String string = getString(SETTINGS, context, KEY_USER_LANGUAGE);
        boolean empty = StringUtils.isEmpty(string);
        if (empty) {
            string = "zh";
        }
        return string;
    }

    /**
     * 保存语言
     *
     * @param context
     * @param language
     */
    public static void saveUserLanguage(Context context, String language) {
        putString(SETTINGS, context, KEY_USER_LANGUAGE, language);
    }
//
//    /**
//     * 获取是否显示启动广告页
//     */
//    public static boolean isShowStartADPage(Context context) {
//        return getBoolean(SETTINGS, context, KEY_SHOW_START_AD);
//    }
//
//    /**
//     * 存储是否显示启动广告页
//     */
//    public static void setShowStartADPage(Context context, boolean value) {
//        putBoolean(SETTINGS, context, KEY_SHOW_START_AD, value);
//    }
}
