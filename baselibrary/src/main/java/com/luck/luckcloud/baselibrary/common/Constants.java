package com.luck.luckcloud.baselibrary.common;

import com.luck.luckcloud.baselibrary.R;
import com.luck.luckcloud.baselibrary.utils.LogUtils;

/**
 * 全局常量
 * Created by 发 on 2017/4/30.
 */

public class Constants {

    private Constants() {
    }

    public static void init() {
        LogUtils.setLogMark("LuckCloud");// 日志的Mark
    }

    // 是否debug模式=======》打包上线改为 false
    public static boolean isDebug = false;
    // 是否显示日志=======》打包上线改为 false
    public static final boolean isShowLog = true;
    // 支付宝是否沙箱模式=======》打包上线改为false
    public static final boolean isALiSanBox = false;
    // 友盟是否使用测试AppKey=======》打包上线改为false
    public static final boolean isUMDebug = false;
    // 环信是否debug模式=======》打包上线改为false
    public static final boolean isIMDebug = false;
    public static final boolean aLiSanBox = false;

    // 是否保存日志到本地
    public static final boolean isSaveLog = false;
    // 崩溃后要启动的Activity的全类名
    public static final String restartActivityName = "";
    // 存放加密密钥的Header的Key
    public static final String encryptRequestHeaderKey = "encrypt-key";
    // Activity是否支持矢量图
    public static final boolean isCompatVectorSupport = true;

    //用户级别颜色
    public static final int[] leaves = {R.color.color_black,
            R.color.color_black,
            R.color.color_pink,
            R.color.color_red,
            R.color.color_yellow,
            R.color.color_green,
            R.color.color_blue,
            R.color.color_purple};

    //所有策略
    public static final String[] celues = {"保守策略", "保守策略", "标准策略", "混合策略", "追涨策略", "叠浪策略", "震荡策略"};

    //执行方式
    public static final String[] loopWays = {"全部", "策略循环", "单次策略", "停止补单", "清仓卖出"};
}
