package com.luck.luckcloud.framelibrary.http;

import android.content.Context;

import com.luck.luckcloud.baselibrary.http.HttpUtils;
import com.luck.luckcloud.baselibrary.utils.StringUtils;
import com.luck.luckcloud.baselibrary.common.Constants;
import com.luck.luckcloud.framelibrary.entity.User;
import com.luck.luckcloud.framelibrary.http.callback.DefaultHttpCallback;

import java.util.List;
import java.util.Map;


/**
 * 接口请求工具类
 * Created by fa on 2017/6/19.
 */
public class HttpRequestUtils {
    private static final String TAG = HttpRequestUtils.class.getSimpleName();

    private HttpRequestUtils() {
    }

    // 线上环境
    private static final String SERVICE_ADDRESS_BASE_ONLINE = "https://www.luokeyun.net/user";
    // 测试环境
    private static final String SERVICE_ADDRESS_BASE_TEST = "https://www.luokeyun.net/user";
    //图片文件前缀
    public static final String SERVICE_ADDRESS_IMAGE = "http://47.242.255.32:8888/";

    /**
     * 根据是否Debug模式获取地址前缀
     */
    public static String getBaseAddress() {
        if (Constants.isDebug) {
            return SERVICE_ADDRESS_BASE_TEST;
        } else {
            return SERVICE_ADDRESS_BASE_ONLINE;
        }
    }

    // 发送短信验证码接口
    private static final String SEND_SMS = getBaseAddress() + "/user/sendPhoneVerifyCode.json";
    // 注册接口
    private static final String REGISTER = getBaseAddress() + "/register";
    // 密保问题接口
    private static final String REGISTER_QUESTIONS = getBaseAddress() + "/register/questions";
    // 获取验证码接口
    private static final String REGISTER_CODE = getBaseAddress() + "/captcha/generate";
    // 获取注册协议
    private static final String REGISTER_AGREEMENT = getBaseAddress() + "/register/agreement";
    // 修改密码
    private static final String PASSWORD_MODIFY = getBaseAddress() + "/security/password/modify";
    // 修改密保
    private static final String ANSWER_MODIFY = getBaseAddress() + "/security/answer/modify";


    // 获取Banner接口
    private static final String MAIN_BANNER_INFO = getBaseAddress() + "/home/advs";
    // 获取推荐网站接口
    private static final String MAIN_WEB_INFO = getBaseAddress() + "/home/sites";
    // 获取公告列表接口
    private static final String MAIN_NOTICES_LIST = getBaseAddress() + "/home/notices";
    // 获取资讯列表接口
    private static final String MAIN_INFOS = getBaseAddress() + "/home/infos";
    // 获取首页交易对接口
    private static final String MAIN_SYMBOLS = getBaseAddress() + "/home/symbols";
    // 获取首页交易对 行情
    private static final String MAIN_MARKETS = getBaseAddress() + "/home/markets";

    // 获取量化列表
    private static final String QUANTI_GROUPS = getBaseAddress() + "/quanti/groups";
    // 获取策略类型
    private static final String STRATEGY_TYPES = getBaseAddress() + "/quanti/strategy/types";
    // 获取量化对应的币列表
    private static final String QUANTI_GROUPS_COINS = getBaseAddress() + "/quanti/group/symbols";
    //开始量化
    private static final String QUANTI_MAKE = getBaseAddress() + "/quanti/make";
    // 获取量化模式
    private static final String START_LIANG_HUA = getBaseAddress() + "/quanti/mode/apply";

    // 交易账户余额
    private static final String BALANCE_SPOT_USDT = getBaseAddress() + "/exch/balance/spot/usdt";
    // 各个策略下的交易币种
    private static final String TRADE_STRATEGYS = getBaseAddress() + "/trade/user/strategys";
    // 单个交易币种的详情
    private static final String TRADE_STRATEGY_LOOP = getBaseAddress() + "/trade/strategy/loop";
    // 变更执行方式
    private static final String TRADE_LOOPWAY_CHANGE = getBaseAddress() + "/trade/loop/way/change";

    // 交易记录列表
    private static final String TRADE_RECORDS = getBaseAddress() + "/trade/records";
    // 交易汇总列表
    private static final String TRADE_INCOME_SUMMARY = getBaseAddress() + "/trade/income/summary";
    // 交易汇总明细列表
    private static final String TRADE_INCOME_RECORDS = getBaseAddress() + "/trade/income/records";

    // 军饷汇总列表
    private static final String COMM_SUMMARYS = getBaseAddress() + "/comm/summarys";
    // 军饷汇总明细列表
    private static final String COMM_RECORDS = getBaseAddress() + "/comm/records";

    //明细接口
    private static final String ACCOUNT_FLOWS = getBaseAddress() + "/acc/account/flows";
    //明细分类接口
    private static final String ACCOUNT_FLOW_CATES = getBaseAddress() + "/acc/account/flow/cates";

    // 提现手续费
    private static final String USDT_WITHDRAW_FEES = getBaseAddress() + "/acc/usdt/withdraw/fees";
    // 提现
    private static final String USDT_WITHDRAW = getBaseAddress() + "/acc/usdt/withdraw";
    // 转币
    private static final String COMM_TRANSFER = getBaseAddress() + "/acc/comm/transfer";
    // 转换
    private static final String COMM_TRANSFORM = getBaseAddress() + "/acc/comm/transform";
    // 充值LC
    private static final String LC_RECHARGE = getBaseAddress() + "/acc/lc/recharge";

    //账户余额查询
    private static final String ACC_ACCOUNTS = getBaseAddress() + "/acc/accounts";
    //获取用户trc20usdt 充币地址
    private static final String ACC_TRC20_USDT = getBaseAddress() + "/acc/trc20/usdt";
    //同步trc20usdt
    private static final String ACC_TRC20_USDT_SYNC = getBaseAddress() + "/acc/trc20/usdt/sync";
    //获取用户波场trx账户
    private static final String ACC_TRX = getBaseAddress() + "/acc/trx";


    // 获取API列表
    private static final String EXCH_AUTHZS = getBaseAddress() + "/exch/authzs";
    // 获取API教程
    private static final String AUTHZ_TUTORIAL = getBaseAddress() + "/exch/authz/tutorial";
    // 添加API授权
    private static final String AUTHZ_ADD = getBaseAddress() + "/exch/authz/add";
    // 删除API授权
    private static final String AUTHZ_DELETE = getBaseAddress() + "/exch/authz/delete";


    //联系客服
    private static final String CONTACT_SERVICE = getBaseAddress() + "/system/base/settings";
    //更新信息
    private static final String UPDATE_INFO = getBaseAddress() + "/system/app/latest";
    //交易日志
    private static final String TRADE_LOGS = getBaseAddress() + "/trade/logs";


    // 登录接口
    private static final String LOGIN = getBaseAddress() + "/login";
    // 获取个人信息接口
    private static final String GET_USER_INFO = getBaseAddress() + "/user/findPersonal.json";

    // 头像图片上传接口
    private static final String UPLOAD_IMAGE = getBaseAddress() + "/user/faceUpload.json";
    // 日志文件上传接口
    private static final String UPLOAD_FILE = getBaseAddress() + "/log/logUpload.json";

    // 退出登录接口
    private static final String LOGOUT = getBaseAddress() + "/logout";

    //***************************************接口方法********************************************//


    //***************************************H5地址********************************************//

    public static final String URL_AGREEMENT = "https://11learned.com/c_user_agreement.html";
    public static final String URL_PRIVACY = "https://11learned.com/c_privacy.html";
    public static final String URL_LESSON_DETAILS_H5 = "https://11learned.com/h5/lession";

    //***************************************H5地址********************************************//


    //***************************************图片地址********************************************//
    public static final String DIALOG_CIRCLE_PART = "https://cdn.11learned.com/xuehuiquangongyue.png";
    public static final String DIALOG_DAYI_SYNOPSIS = "https://cdn.11learned.com/da_yi_shi_jie_shao.jpg";
    public static final String DIALOG_ZIXI_SYNOPSIS = "https://cdn.11learned.com/zi_xi_shi_jie_shao.jpg";
    //***************************************H5地址********************************************//

    /**
     * 注册接口
     *
     * @param context
     * @param username            用户名
     *                            //     * @param registerSource      注册来源 1手机号 2邮箱 3用户名 4qq 5微信 6新浪
     * @param firstQuestionId     第一个密保问题的id
     * @param firstQuestionValue  第一个密保问题的答案
     * @param secondQuestionId    第二个密保问题的id
     * @param secondQuestionValue 第二个密保问题的答案
     * @param realName            真实姓名
     * @param idNo                身份证号码
     * @param password            用户密码
     * @param confirmPass         确认密码
     * @param paypass             支付密码
     * @param inviteCode          好友邀请码
     * @param callback
     * @param <T>
     */
    public static <T> void register(Context context, String username, String firstQuestionId, String firstQuestionValue, String secondQuestionId,
                                    String secondQuestionValue, String realName, String idNo, String password, String confirmPass, String paypass,
                                    String inviteCode, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(REGISTER)
                .addParams("username", username)
                .addParams("registerSource", 1)
                .addParams("firstQuestionId", firstQuestionId)
                .addParams("firstQuestionValue", firstQuestionValue)
                .addParams("secondQuestionId", secondQuestionId)
                .addParams("secondQuestionValue", secondQuestionValue)
                .addParams("realName", realName)
                .addParams("idNo", idNo)
                .addParams("password", password)
                .addParams("confirmPass", confirmPass)
                .addParams("paypass", paypass)
                .addParams("inviteCode", inviteCode)
                .execute(callback);
    }

    /**
     * 密保问题列表
     *
     * @param context  上下文
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void registerQuestion(Context context, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(REGISTER_QUESTIONS)
                .execute(callback);
    }

    /**
     * 验证码
     *
     * @param context  上下文
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void registerCode(Context context, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(REGISTER_CODE)
                .execute(callback);
    }

    /**
     * 注册协议
     *
     * @param context  上下文
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void registerText(Context context, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(REGISTER_AGREEMENT)
                .execute(callback);
    }

    /**
     * 用户登录接口
     *
     * @param context  上下文
     * @param callback 回调
     * @param password 密码
     * @param <T>      泛型
     */
    public static <T> void login(Context context, String name, String password, String codeId, String codeText, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(LOGIN)
                .addParams("username", name)
                .addParams("password", password)
                .addParams("captchaId", codeId)
                .addParams("captchaText", codeText)
                .execute(callback);
    }

    /**
     * 首页banner
     *
     * @param context  上下文
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void getMainBannerInfo(Context context, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(MAIN_BANNER_INFO)
                .execute(callback);
    }

    /**
     * 首页web按钮
     *
     * @param context  上下文
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void getMainWebList(Context context, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(MAIN_WEB_INFO)
                .execute(callback);
    }

    /**
     * 发送短信验证码
     *
     * @param context  上下文
     * @param phone    手机号
     * @param type     业务类型 01 学生注册 02学生密码找回 03学校注册
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void sendSMS(Context context, String phone, String type, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .postString()
                .url(SEND_SMS)
                .addParams("phone", phone)
                .addParams("type", type)
                .execute(callback);
    }

    /**
     * 查询用户信息
     *
     * @param context     上下文
     * @param otherUserId 用户ID  otherUserId 为空 查登录用户的个人信息   不为空 查他人个人信息
     * @param callback    回调
     * @param <T>         泛型
     */
    public static <T> void getUserInfo(Context context, String otherUserId, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .postString()
                .url(GET_USER_INFO)
                .addParams("otherUserId", otherUserId)
                .execute(callback);
    }


    /**
     * 头像图片上传
     *
     * @param context  上下文
     * @param imageStr 图片base64字符串
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void uploadImage(Context context, String imageStr, String token, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(UPLOAD_IMAGE)
                .addHeader(DefaultHttpCallback.KEY_TOKEN, token)
                .addParams("faceImage", imageStr)
                .execute(callback);
    }

    /**
     * 日志文件上传
     *
     * @param context  上下文
     * @param fileStr  log文件base64字符串
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void uploadFile(Context context, String fileStr, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .postString()
                .url(UPLOAD_FILE)
                .addParams("logFile", fileStr)
                .execute(callback);
    }


    /**
     * 退出登录
     *
     * @param context  上下文
     * @param callback 回调
     * @param <T>      泛型
     */
    public static <T> void logout(Context context, DefaultHttpCallback<T> callback) {
        String userId = User.getInstance().getUserId(context);
        if (StringUtils.isEmpty(userId)) {
            return;
        }
        HttpUtils.with(context)
                .get()
                .url(LOGOUT)
                .addParams("uid", userId)
                .execute(callback);
    }


    public static <T> void checkUpdate(Context context, DefaultHttpCallback<T> callback) {

    }

    /**
     * 获取首页币列表
     *
     * @param context
     * @param callback
     * @param <T>
     */
    public static <T> void getRecommendList(Context context, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(MAIN_SYMBOLS)
                .execute(callback);
    }

    /**
     * 获取首页币价格
     *
     * @param context
     * @param callback
     * @param <T>
     */
    public static <T> void getMainMarkets(Context context, String symbol, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .addParams("symbol", symbol)
                .url(MAIN_MARKETS)
                .execute(callback);
    }

    /**
     * 获取首页公告
     *
     * @param context
     * @param callback
     * @param <T>
     */
    public static <T> void getMainNoticeList(Context context, int pageNo, int pageSize, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(MAIN_NOTICES_LIST)
                .execute(callback);
    }

    /**
     * 修改密码
     *
     * @param context
     * @param uid
     * @param type         密码类型 1 登录密码 2 支付密码
     * @param questionCode
     * @param question
     * @param password
     * @param password1
     * @param callback
     * @param <T>
     */
    public static <T> void passwordModify(Context context, String uid, int type, String questionCode, String question, String password, String password1, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(PASSWORD_MODIFY)
                .addParams("uid", uid)
                .addParams("type", type)
                .addParams("questionId", questionCode)
                .addParams("questionValue", question)
                .addParams("password", password)
                .addParams("confirmPass", password1)
                .execute(callback);
    }

    /**
     * 修改密保问题
     *
     * @param context
     * @param uid
     * @param oldQuestionId    原密保问题的id
     * @param oldQuestionValue 原密保问题的答案
     * @param newQuestionId    新密保问题的id
     * @param newQuestionValue 新密保问题的答案
     * @param callback
     * @param <T>
     */
    public static <T> void secretModify(Context context, String uid, String oldQuestionId, String oldQuestionValue, String newQuestionId, String newQuestionValue, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(ANSWER_MODIFY)
                .addParams("uid", uid)
                .addParams("oldQuestionId", oldQuestionId)
                .addParams("oldQuestionValue", oldQuestionValue)
                .addParams("newQuestionId", newQuestionId)
                .addParams("newQuestionValue", newQuestionValue)
                .execute(callback);
    }

    /**
     * 获取API教程
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void getApiTutorial(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(AUTHZ_TUTORIAL)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 添加API授权
     *
     * @param context
     * @param uid
     * @param exchCode  交易所编码 默认 HUOBI
     * @param authzType 授权类型 默认 3 API
     * @param accType   账户类型 默认 1-现货
     * @param accessKey 访问key
     * @param secretKey 加密key
     * @param callback
     * @param <T>
     */

    public static <T> void apiAuthzAdd(Context context, String uid, String exchCode, String authzType, String accType, String accessKey, String secretKey, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(AUTHZ_ADD)
                .addParams("uid", uid)
                .addParams("exchCode", exchCode)
                .addParams("authzType", authzType)
                .addParams("accType", accType)
                .addParams("accessKey", accessKey)
                .addParams("secretKey", secretKey)
                .execute(callback);
    }

    /**
     * 删除API 授权
     *
     * @param context
     * @param uid
     * @param id
     * @param callback
     * @param <T>
     */
    public static <T> void deleteApiAuth(Context context, String uid, String id, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(AUTHZ_DELETE)
                .addParams("uid", uid)
                .addParams("id", id)
                .execute(callback);
    }

    /**
     * 联系客服信息
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void contactService(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(CONTACT_SERVICE)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 检查更新信息
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void getUpdateInfo(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(UPDATE_INFO)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 获取量化列表
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void quantiGroups(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(QUANTI_GROUPS)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 获取策略类型
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void strategyTypes(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(STRATEGY_TYPES)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 量化币种列表
     *
     * @param context
     * @param uid
     * @param gid
     * @param callback
     * @param <T>
     */
    public static <T> void lianghuaCoins(Context context, String uid, String gid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(QUANTI_GROUPS_COINS)
                .addParams("uid", uid)
                .addParams("gid", gid)
                .execute(callback);
    }

    /**
     * 开始量化
     *
     * @param context
     * @param uid              用户号
     * @param firstQuoteAmount 首单金额 报价币种金额 小数点后两位, 不小于10
     * @param data
     * @param callback
     * @param <T>
     */
    public static <T> void startLiangHua(Context context, String uid, String firstQuoteAmount, String data, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(QUANTI_MAKE)
                .addParams("uid", uid)
                .addParams("firstQuoteAmount", firstQuoteAmount)
                .addParams("data", data)
                .execute(callback);
    }

    /**
     * 开始量化  多个币种
     *
     * @param context
     * @param uid               用户号
     * @param firstQuoteAmount  首单金额 报价币种金额 小数点后两位, 不小于10
     * @param maxPosQuoteAmount 最大持仓金额 花费的报价币种金额 基金版开通
     * @param data
     * @param callback
     * @param <T>
     */
    public static <T> void startLiangHua(Context context, String uid, String firstQuoteAmount, String maxPosQuoteAmount, String typeNo, String data, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(QUANTI_MAKE)
                .addParams("uid", uid)
                .addParams("typeNo", typeNo)
                .addParams("firstQuoteAmount", firstQuoteAmount)
                .addParams("maxPosQuoteAmount", maxPosQuoteAmount)
                .addParams("data", data)
                .execute(callback);
    }

    /**
     * 开始量化  单个币种
     *
     * @param context
     * @param uid               用户号
     * @param firstQuoteAmount  首单金额 报价币种金额 小数点后两位, 不小于10
     * @param maxPosQuoteAmount 最大持仓金额 花费的报价币种金额 基金版开通
     * @param symbol
     * @param callback
     * @param <T>
     */
    public static <T> void startLiangHuaCoin(Context context, String uid, String firstQuoteAmount, String maxPosQuoteAmount, String typeNo, String symbol, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(QUANTI_MAKE)
                .addParams("uid", uid)
                .addParams("typeNo", typeNo)
                .addParams("firstQuoteAmount", firstQuoteAmount)
                .addParams("maxPosQuoteAmount", maxPosQuoteAmount)
                .addParams("symbol", symbol)
                .execute(callback);
    }

    /**
     * 获取量化模式
     *
     * @param context
     * @param uid
     * @param mode
     * @param callback
     * @param <T>
     */
    public static <T> void liangHuaMode(Context context, String uid, String mode, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(START_LIANG_HUA)
                .addParams("uid", uid)
                .addParams("mode", mode)
                .execute(callback);
    }

    /**
     * 交易账户余额
     *
     * @param context
     * @param uid
     * @param exchCode  交易所编码 默认 HUOBI
     * @param authzType 授权类型 3 API
     * @param accType   账号类型 1-现货
     * @param callback
     * @param <T>
     */
    public static <T> void balanceSpotUsdt(Context context, String uid, String exchCode, String authzType, String accType, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(BALANCE_SPOT_USDT)
                .addParams("uid", uid)
                .addParams("exchCode", exchCode)
                .addParams("authzType", authzType)
                .addParams("accType", accType)
                .execute(callback);
    }

    /**
     * 获取不同策略的交易币种
     *
     * @param context
     * @param uid
     * @param symbol
     * @param loopWay
     * @param callback
     * @param <T>
     */
    public static <T> void tradeStrategys(Context context, String uid, String symbol, int loopWay, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(TRADE_STRATEGYS)
                .addParams("uid", uid)
                .addParams("symbol", symbol)
                .addParams("loopWay", loopWay)
                .execute(callback);
    }

    /**
     * 获取单个交易币种的详情
     *
     * @param context
     * @param uid
     * @param straId   id
     * @param callback
     * @param <T>
     */
    public static <T> void tradeStrategyLoop(Context context, String uid, String straId, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(TRADE_STRATEGY_LOOP)
                .addParams("uid", uid)
                .addParams("straId", straId)
                .execute(callback);
    }

    /**
     * 查询交易列表
     *
     * @param context
     * @param uid       用户号
     * @param orderType 订单类型 11 买入, 21 卖出 传0表示查询所有类型
     * @param symbol    交易对 传空表示查询所有交易对
     * @param startDate 开始日期 YYYYMMDD 传0查所有成交日
     * @param endDate   结束日期 YYYYMMDD 传0查所有成交日
     * @param pageNo    当前页码
     * @param callback
     * @param <T>
     */
    public static <T> void tradeRecords(Context context, String uid, String orderType, String symbol, String startDate, String endDate, int pageNo, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(TRADE_RECORDS)
                .addParams("uid", uid)
                .addParams("orderType", orderType)
                .addParams("symbol", symbol)
                .addParams("startDate", startDate)
                .addParams("endDate", endDate)
                .addParams("pageNo", pageNo)
                .addParams("pageSize", 20)
                .execute(callback);
    }

    /**
     * 我的收益页面
     * 收益汇总列表
     *
     * @param context
     * @param uid
     * @param symbol      交易对 传空表示查询所有交易对
     * @param incomeMonth 收益月 YYYYMM 0为年的所有月
     * @param incomeYear  收益年 YYYY 0为所有年
     * @param pageNo      当前页码
     * @param callback    每页记录数
     * @param <T>
     */
    public static <T> void tradeIncomeSummary(Context context, String uid, String symbol, String incomeMonth, String incomeYear, int pageNo, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(TRADE_INCOME_SUMMARY)
                .addParams("uid", uid)
                .addParams("symbol", symbol)
                .addParams("incomeDate", 0)
                .addParams("incomeMonth", incomeMonth)
                .addParams("incomeYear", incomeYear)
                .addParams("pageNo", pageNo)
                .addParams("pageSize", 20)
                .execute(callback);
    }

    /**
     * @param context
     * @param uid
     * @param symbol     交易对 传空表示查询所有交易对
     * @param incomeDate 收益日 YYYYMMDD 传0查所有收益日
     * @param pageNo
     * @param callback
     * @param <T>
     */
    public static <T> void tradeIncomeRecords(Context context, String uid, String symbol, String incomeDate, int pageNo, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(TRADE_INCOME_RECORDS)
                .addParams("uid", uid)
                .addParams("symbol", symbol)
                .addParams("incomeDate", incomeDate)
                .addParams("pageNo", pageNo)
                .addParams("pageSize", 20)
                .execute(callback);
    }

    /**
     * @param context
     * @param uid
     * @param commDate  分佣日 YYYYMMDD 0为月的所有天
     * @param commMonth 分佣月 YYYYMM 0为年的所有月
     * @param commYear  分佣年 YYYY 0为所有年
     * @param pageNo
     * @param callback
     * @param <T>
     */
    public static <T> void commSummarys(Context context, String uid, String commDate, String commMonth, String commYear, int pageNo, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(COMM_SUMMARYS)
                .addParams("uid", uid)
                .addParams("commDate", commDate)
                .addParams("commMonth", commMonth)
                .addParams("commYear", commYear)
                .addParams("pageNo", pageNo)
                .addParams("pageSize", 20)
                .execute(callback);
    }

    /**
     * @param context
     * @param uid
     * @param symbol   交易对 传空表示查询所有交易对
     * @param commDate 分佣日 YYYYMMDD 传0查所有分佣日
     * @param pageNo
     * @param callback
     * @param <T>
     */
    public static <T> void commRecords(Context context, String uid, String symbol, String commDate, int pageNo, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(COMM_RECORDS)
                .addParams("uid", uid)
                .addParams("symbol", symbol)
                .addParams("commDate", commDate)
                .addParams("pageNo", pageNo)
                .addParams("pageSize", 20)
                .execute(callback);
    }

    /**
     * 获取账户余额
     *
     * @param context
     * @param uid
     * @param subjectId 科目号 10 LC账户 11 分佣账户, 12 U账户 ,20 TRX账户, 21 TRC20合约账户, 多个科目用,隔开, 注意不会同步外部账户余额
     * @param callback
     * @param <T>
     */
    public static <T> void accAccounts(Context context, String uid, String subjectId, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(ACC_ACCOUNTS)
                .addParams("uid", uid)
                .addParams("subjectId", subjectId)
                .execute(callback);
    }

    /**
     * 获取acc trc20 usdt 充币地址
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void accTrc20Usdt(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(ACC_TRC20_USDT)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 获取用户波场trx账户
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void accTrx(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(ACC_TRX)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 获取已授权API列表
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void reserveApiList(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(EXCH_AUTHZS)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 转币
     *
     * @param context
     * @param uid            用户号
     * @param targetUsername 目标用户账号
     * @param tansferAmount  tansferAmount
     * @param paypass
     * @param callback
     * @param <T>
     */
    public static <T> void commTransfer(Context context, String uid, String targetUsername, String tansferAmount, String paypass, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(COMM_TRANSFER)
                .addParams("uid", uid)
                .addParams("targetUsername", targetUsername)
                .addParams("tansferAmount", tansferAmount)
                .addParams("paypass", paypass)
                .execute(callback);
    }

    /**
     * @param context
     * @param uid
     * @param transformType   转换类型 1 转LC 2 转USDT
     * @param transformAmount 转换数量
     * @param paypass
     * @param callback
     * @param <T>
     */
    public static <T> void commTransform(Context context, String uid, int transformType, String transformAmount, String paypass, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(COMM_TRANSFORM)
                .addParams("uid", uid)
                .addParams("transformType", transformType)
                .addParams("transformAmount", transformAmount)
                .addParams("paypass", paypass)
                .execute(callback);
    }

    /**
     * 充值LC
     *
     * @param context
     * @param uid
     * @param rechargeAmount
     * @param paypass
     * @param callback
     * @param <T>
     */
    public static <T> void lcRecharge(Context context, String uid, String rechargeAmount, String paypass, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(LC_RECHARGE)
                .addParams("uid", uid)
                .addParams("rechargeAmount", rechargeAmount)
                .addParams("paypass", paypass)
                .execute(callback);
    }

    /**
     * 获取提现手续费
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void usdtWithdrawFees(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(USDT_WITHDRAW_FEES)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 提现
     *
     * @param context
     * @param uid
     * @param targetAddress
     * @param withdrawAmount
     * @param paypass
     * @param callback
     * @param <T>
     */
    public static <T> void usdtWithdraw(Context context, String uid, String targetAddress, String withdrawAmount, String paypass, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(USDT_WITHDRAW)
                .addParams("uid", uid)
                .addParams("targetAddress", targetAddress)
                .addParams("withdrawAmount", withdrawAmount)
                .addParams("paypass", paypass)
                .execute(callback);
    }

    /**
     * 变更 某一种币种 执行方式
     *
     * @param context
     * @param uid
     * @param straId
     * @param fromLoopWay
     * @param toLoopWay
     * @param callback
     * @param <T>
     */
    public static <T> void tradeLoopwayChange(Context context, String uid, String straId, int fromLoopWay, int toLoopWay, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(TRADE_LOOPWAY_CHANGE)
                .addParams("uid", uid)
                .addParams("straId", straId)
                .addParams("fromLoopWay", fromLoopWay)
                .addParams("toLoopWay", toLoopWay)
                .execute(callback);
    }

    /**
     * 变更 全部币种 执行方式
     *
     * @param context
     * @param uid
     * @param toLoopWay
     * @param callback
     * @param <T>
     */
    public static <T> void tradeLoopwayChange(Context context, String uid, int toLoopWay, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(TRADE_LOOPWAY_CHANGE)
                .addParams("uid", uid)
                .addParams("toLoopWay", toLoopWay)
                .execute(callback);
    }

    /**
     * 变更 某种策略下的全部币种 执行方式
     *
     * @param context
     * @param uid
     * @param toLoopWay
     * @param callback
     * @param <T>
     */
    public static <T> void tradeLoopwayChange(Context context, String uid, int fromLoopWay, int toLoopWay, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(TRADE_LOOPWAY_CHANGE)
                .addParams("uid", uid)
                .addParams("fromLoopWay", fromLoopWay)
                .addParams("toLoopWay", toLoopWay)
                .execute(callback);
    }

    /**
     * 同步 usdt
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void accTrc20UsdtSync(Context context, String uid, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .post()
                .url(ACC_TRC20_USDT_SYNC)
                .addParams("uid", uid)
                .execute(callback);
    }

    /**
     * 交易日志
     *
     * @param context
     * @param uid
     * @param callback
     * @param <T>
     */
    public static <T> void tradeLogs(Context context, String uid, String startDate, String endDate, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(TRADE_LOGS)
                .addParams("uid", uid)
//                .addParams("startDate", 0)
//                .addParams("endDate", 0)
                .execute(callback);
    }

    /**
     * 明细接口
     *
     * @param context
     * @param uid
     * @param subjectId 科目号 10 LC账户 11 募兵账户 12 U账户
     * @param chgCate   变更大类
     * @param chgMonth  变更月 格式 yyyyMM
     * @param pageNo    当前页码
     * @param callback
     * @param <T>
     */
    public static <T> void accountFlows(Context context, String uid, String subjectId, String chgCate, String chgMonth, int pageNo, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(ACCOUNT_FLOWS)
                .addParams("uid", uid)
                .addParams("subjectId", subjectId)
                .addParams("chgCate", chgCate)
                .addParams("chgMonth", chgMonth)
                .addParams("pageNo", pageNo)
                .addParams("pageSize", 20)
                .execute(callback);
    }

    /**
     * 明细分类接口
     *
     * @param context
     * @param uid
     * @param subjectId
     * @param callback
     * @param <T>
     */
    public static <T> void accountFlowCates(Context context, String uid, String subjectId, DefaultHttpCallback<T> callback) {
        HttpUtils.with(context)
                .get()
                .url(ACCOUNT_FLOW_CATES)
                .addParams("uid", uid)
                .addParams("subjectId", subjectId)
                .execute(callback);
    }
}
