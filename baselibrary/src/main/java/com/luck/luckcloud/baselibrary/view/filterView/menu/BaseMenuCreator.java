package com.luck.luckcloud.baselibrary.view.filterView.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.luck.luckcloud.baselibrary.entity.BaseFilterMenuEntity;
import com.luck.luckcloud.baselibrary.view.filterView.MenuView;

import java.util.List;

/**
 * 底部菜单生成器
 * author: fa
 * date: 2018/3/14 08:51.
 */

public abstract class BaseMenuCreator {
    private static final String TAG = BaseMenuCreator.class.getSimpleName();

    protected Context mContext;
    // 底部菜单的回调
    protected MenuCreatorCallback mCallback;
    // 底部菜单列表的父布局
    protected MenuView mMenuView;
    // View
    protected View mRootView;

    public BaseMenuCreator(@NonNull Context context) {
        this.mContext = context;
    }

    /**
     * 初始化方法
     */
    public abstract void initView();

    /**
     * 获取菜单View
     */
    public abstract View getMenuView();

    /**
     * 获取与标题对应的索引，避免两个标题中间夹着一个不用显示菜单的标题
     */
    public abstract int getIndex();

    /**
     * 底部菜单列表的父布局
     */
    public void setFilterBottomMenu(MenuView menuView) {
        this.mMenuView = menuView;
    }

    /**
     * 刷新数据显示
     */
    public abstract void refreshViewShow(List<BaseFilterMenuEntity> data);

    /**
     * 重置菜单
     */
    public void resetMenu() {
    }

//    /**
//     * 菜单关闭的时候回调
//     */
//    public void onMenuClose() {
//
//    }
//

    /**
     * 菜单显示的时候回调
     */
    public void onMenuShow() {

    }

    /**
     * 设置回调
     */
    public void setMenuCallback(MenuCreatorCallback callback) {
        this.mCallback = callback;
    }

    /**
     * 设置菜单的回显数据
     */
    public void setMenuDisplayData(int index, String... id) {
    }

    /**
     * 底部菜单条目点击的回调
     */
    public interface MenuCreatorCallback {

        void onSelectedCondition(Object conditionId, String showCondition);
    }
}
