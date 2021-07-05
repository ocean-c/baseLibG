package com.luck.luckcloud.baselibrary.entity;

/**
 * Home按键监听的通知实体
 * author: fa
 * date: 2018/5/8 09:49.
 */

public class HomeKeyEvent {
    public static final int ACTION_HOME_KEY_PRESS = 1;//按下Home按键
    public static final int ACTION_HOME_KEY_PRESS_LONG = 2;// 长按Home按键
    private int homeKeyAction;

    public int getHomeKeyAction() {
        return homeKeyAction;
    }

    public void setHomeKeyAction(int homeKeyAction) {
        this.homeKeyAction = homeKeyAction;
    }
}
