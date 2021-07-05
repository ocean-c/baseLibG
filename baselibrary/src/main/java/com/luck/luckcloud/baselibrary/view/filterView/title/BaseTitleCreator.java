package com.luck.luckcloud.baselibrary.view.filterView.title;

import android.content.Context;
import android.view.View;

import com.luck.luckcloud.baselibrary.utils.StringUtils;


/**
 * 筛选菜单标题栏的构建器，默认是均分布局
 * Created by fa on 2018/9/18.
 */

public abstract class BaseTitleCreator {
    private static final String TAG = BaseTitleCreator.class.getSimpleName();
    protected Context mContext;
    protected View mRootView;
    private MenuTitleCallback mCallback;// 标题点击的回调
    private int mCurrentShowIndex = -1;// 当前显示菜单的TitleItem的索引

    public BaseTitleCreator(Context mContext) {
        this.mContext = mContext;
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        // 创建标题View
        mRootView = createTitleView();
        // 初始化各个小标题的点击事件
        for (int i = 0; i < getTitleCount(); i++) {
            View childView = getTitleItemByIndex(i);
            childView.setOnClickListener(new MyTitleItemClickListener(i));
        }
    }

    /**
     * 获取创建的TitleView
     */
    public View getRootView() {
        return mRootView;
    }

    /**
     * 重置索引
     */
    public void resetIndex() {
        mCurrentShowIndex = -1;
    }

    /**
     * 标题单个条目的点击事件
     */
    private class MyTitleItemClickListener implements View.OnClickListener {
        private int mIndex;

        MyTitleItemClickListener(int index) {
            this.mIndex = index;
        }

        @Override
        public void onClick(View view) {
            // 处理标题的点击状态改变，关闭的状态
            if (mCurrentShowIndex == mIndex) {// 点击的当前显示菜单的标题
                onMenuShowStateChange(mIndex, false);
                onMenuClose(mIndex, needShowMenu(mIndex));
                mCurrentShowIndex = -1;
            } else {// 点击的其他标题
                onMenuShowStateChange(mIndex, true);
                onMenuOpen(mIndex, needShowMenu(mIndex));
                // 上次选中的置为关闭状态（判断初始值-1）
                if (mCurrentShowIndex != -1) {
                    onMenuClose(mCurrentShowIndex, needShowMenu(mCurrentShowIndex));
                }
                // 索引赋值
                mCurrentShowIndex = mIndex;
            }
            // 标题点击回调
            if (mCallback != null) {
                mCallback.onClickTitle(mIndex, needShowMenu(mIndex), getTitleItemByIndex(mIndex));
            }
        }
    }


    /**
     * 设置标题回显选择的条件
     */
    public void setTitleShowCondition(int index, String showCondition) {
        // 只有在该Title可以显示菜单的时候才会有条件回显
        if (needShowMenu(index)) {
            if (StringUtils.isEmpty(showCondition)) {
                onSelectDefault(index);
            } else {
                onSelectCondition(index, showCondition);
            }
        }
    }

    /**
     * 重置标题显示
     */
    public void resetTitle() {
        for (int i = 0; i < getTitleCount(); i++) {
            onTitleViewReset(i);
        }
    }

    /**
     * 菜单可见状态变化的时候回调
     */
    public void onMenuShowStateChange(int index, boolean show) {
    }

    /**
     * 菜单展开的时候回调
     */
    protected void onMenuOpen(int index, boolean needShowMenu) {
        View titleView = getTitleItemByIndex(index);
        if (titleView instanceof FilterTitleView) {
            ((FilterTitleView) titleView).setMenuOpen(true);
        }
    }

    /**
     * 当菜单关闭的时候回调
     */
    public void onMenuClose(int index, boolean needShowMenu) {
        View titleView = getTitleItemByIndex(index);
        if (titleView instanceof FilterTitleView) {
            ((FilterTitleView) titleView).setMenuOpen(false);
        }
    }

    /**
     * 选择了默认条件之后回调
     */
    protected void onSelectDefault(int index) {
        View titleView = getTitleItemByIndex(index);
        if (titleView instanceof FilterTitleView) {
            ((FilterTitleView) titleView).setTitleText(getDefaultTitleText(index));
            ((FilterTitleView) titleView).setSelectedCondition(false);
        }
    }

    /**
     * 选择了普通条件之后回调
     */
    protected void onSelectCondition(int index, String showCondition) {
        View titleView = getTitleItemByIndex(index);
        if (titleView instanceof FilterTitleView) {
            ((FilterTitleView) titleView).setTitleText(showCondition);
            ((FilterTitleView) titleView).setSelectedCondition(true);
        }
    }

    /**
     * 标题状态重置的时候回调
     */
    protected void onTitleViewReset(int index) {
        View titleView = getTitleItemByIndex(index);
        if (titleView instanceof FilterTitleView) {
            ((FilterTitleView) titleView).setMenuOpen(false);
            ((FilterTitleView) titleView).setSelectedCondition(false);
            ((FilterTitleView) titleView).setTitleText(getDefaultTitleText(index));
        }
    }


    public void setCallback(MenuTitleCallback callback) {
        this.mCallback = callback;
    }

    public interface MenuTitleCallback {
        void onClickTitle(int index, boolean showMenu, View titleView);
    }


    /**
     * 子类去创建标题控件
     */
    protected abstract View createTitleView();

    /**
     * 标题数量
     */
    protected abstract int getTitleCount();

    /**
     * 获取索引位置的标题控件
     */
    protected abstract View getTitleItemByIndex(int index);

    /**
     * 获取标题栏默认显示的文字
     */
    protected String getDefaultTitleText(int index) {
        return null;
    }

    /**
     * 是否需要展开菜单
     */
    protected boolean needShowMenu(int index) {
        return true;
    }
}
