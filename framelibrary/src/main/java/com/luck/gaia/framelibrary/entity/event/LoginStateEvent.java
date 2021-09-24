package com.luck.gaia.framelibrary.entity.event;

/**
 * 登录之后的事件通知实体
 * Created by fa on 2018/7/25.
 */
public class LoginStateEvent {
    private boolean isLogin;

    public LoginStateEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
