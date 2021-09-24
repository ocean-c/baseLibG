package com.luck.gaia.baselibrary.view.filterView;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.luck.gaia.baselibrary.R;
import com.luck.gaia.baselibrary.base.DefaultActivityLifecycleCallbacks;
import com.luck.gaia.baselibrary.entity.BaseFilterMenuEntity;
import com.luck.gaia.baselibrary.utils.DisplayUtil;
import com.luck.gaia.baselibrary.view.filterView.data.BaseDataHelper;
import com.luck.gaia.baselibrary.view.filterView.menu.BaseMenuCreator;
import com.luck.gaia.baselibrary.view.filterView.title.BaseTitleCreator;

import java.util.List;

/**
 * 筛选菜单
 * author: fa
 * date: 2018/3/13 16:16.
 */
public abstract class FilterMenuView extends LinearLayout {
    private static final String TAG = FilterMenuView.class.getSimpleName();
    protected Context mContext;
    // 底部菜单的控件
    private MenuView mMenuView;
    // 标题生成器
    private BaseTitleCreator mTitleCreator;
    // 底部菜单的数据的生成器
    protected BaseDataHelper mMenuDataHelper;
    // 条件点击的回调
    private FilterMenuViewCallback mCallback;
    // 是否显示菜单下边分割线
    private View mLineView;// 分割线的View
    // 标题是否显示选择的信息
    private boolean mShowSelect = true;
    private MyActivityLifecycleCallback mLifeCycleCallback;

    public FilterMenuView(Context context) {
        this(context, null);
    }

    public FilterMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        // 使得onKeyDown监听生效
        setFocusable(true);
        setFocusableInTouchMode(true);
        // 设置布局方向
        setOrientation(LinearLayout.VERTICAL);
        // 是否添加分割线
        mLineView = new View(mContext);
        mLineView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_eaeaea));
        mLineView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(mContext, 0.5f)));
        // 添加进去
        addView(mLineView, 0);
        // 初始化菜单控件
        mMenuView = new MenuView(mContext);
        mMenuView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // 设置回调
        mMenuView.setCallback(new MyMenuViewCallback());
        // 添加进去
        addView(mMenuView, 1);
        // 注册Activity的生命周期监听
        mLifeCycleCallback = new MyActivityLifecycleCallback();
        ((Activity) mContext).getApplication().registerActivityLifecycleCallbacks(mLifeCycleCallback);
        // 初始化View
        initView();
    }

    protected void initView() {
        // 标题
        setTitleCreator(getTitleCreator());
        // 菜单
        setMenuCreatorList(getMenuCreatorList());
        // 数据
        setDataHelper(getDataHelper());
        // 回调
        setCallback(getMenuCallback());
    }

    protected abstract BaseTitleCreator getTitleCreator();

    protected abstract List<BaseMenuCreator> getMenuCreatorList();

    protected abstract BaseDataHelper getDataHelper();

    protected abstract FilterMenuViewCallback getMenuCallback();

    /**
     * Activity的生命周期监听
     */
    private class MyActivityLifecycleCallback extends DefaultActivityLifecycleCallbacks {
        @Override
        public void onActivityPaused(Activity activity) {
            // Pause的时候菜单消失
            if (activity == mContext) {
                mMenuView.closeMenu(true);
            }
        }
    }

    /**
     * 点击返回按钮的时候关闭菜单
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mMenuView.isShowing()) {
            mMenuView.closeMenu(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 底部菜单控件的回调
     */
    private class MyMenuViewCallback implements MenuView.MenuViewCallback {

        @Override
        public void onMenuShow(int index) {
            // TODO 菜单显示的时候获取焦点，不然捕获不到onKeyDown事件
            requestFocus();
        }

        @Override
        public void onMenuClose(int index) {
            // 回调标题Creator,控制点击筛选条件菜单关闭的时候，标题UI的刷新
            mTitleCreator.resetIndex();
            mTitleCreator.onMenuClose(index, true);
            mTitleCreator.onMenuShowStateChange(index, false);
        }
    }

    /**
     * 设置数据源
     */
    public void setDataHelper(BaseDataHelper dataHelper) {
        this.mMenuDataHelper = dataHelper;
        if (mMenuDataHelper == null) {
            return;
        }
        mMenuDataHelper.setFilterMenuDataCallback(new BaseDataHelper.FilterMenuDataCallback() {
            @Override
            public void onDataSuccess(int index, List<BaseFilterMenuEntity> data) {
                // 菜单设置数据
                mMenuView.setMenuData(index, data);
            }
        });
    }

    /**
     * 设置标题生成器
     */
    public void setTitleCreator(BaseTitleCreator titleCreator) {
        this.mTitleCreator = titleCreator;
        if (mTitleCreator == null) {
            return;
        }
        titleCreator.setCallback(new MyTitleViewCallback());
        // 移除原来的标题View（判断数量，为3个的时候才移除，否则索引0对应的有可能是横线或者遮罩）
        if (getChildCount() > 2) {
            removeViewAt(0);
        }
        // 获取标题的View
        View childView = mTitleCreator.getRootView();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        // 将标题布局添加到菜单控件中
        addView(childView, 0, params);
    }

    /**
     * 标题点击回调
     */
    private class MyTitleViewCallback implements BaseTitleCreator.MenuTitleCallback {

        @Override
        public void onClickTitle(int index, boolean showMenu, View titleView) {
            if (titleView == null) {
                return;
            }
            if (showMenu) {
                // 展示相应的Menu布局
                mMenuView.showMenuByIndex(index);
            } else {
                mMenuView.closeMenu(true);
            }
            // 点击标题的事件回调到整个筛选控件的引用者
            if (mCallback != null) {
                mCallback.onClickTitleItem(index, titleView);
            }
        }
    }

    /**
     * 初始化的时候通过设置底部菜单生成器，生成底部菜单，注意顺序与标题对应
     */
    public void setMenuCreatorList(List<BaseMenuCreator> menuCreatorList) {
        if (menuCreatorList == null || menuCreatorList.size() == 0) {
            return;
        }
        SparseArray<BaseMenuCreator> menuCreators = new SparseArray<>();
        for (int i = 0; i < menuCreatorList.size(); i++) {
            BaseMenuCreator creator = menuCreatorList.get(i);
            // 初始化View
            creator.initView();
            // 添加回调
            creator.setMenuCallback(new MyMenuCreatorCallback(creator.getIndex()));
            // 将最外层的父布局的引用设置进去，便于调用关闭菜单的方法
            creator.setFilterBottomMenu(mMenuView);
            // 添加到SparseArray
            menuCreators.put(creator.getIndex(), creator);
        }
        // 设置到菜单父布局中
        mMenuView.setMenuCreators(menuCreators);
    }


    /**
     * 底部菜单的回调
     */
    private class MyMenuCreatorCallback implements BaseMenuCreator.MenuCreatorCallback {
        private int mIndex;

        MyMenuCreatorCallback(int index) {
            this.mIndex = index;
        }

        @Override
        public void onSelectedCondition(Object conditionId, String showCondition) {
            // 选择之后
            if (mShowSelect) {
                mTitleCreator.setTitleShowCondition(mIndex, showCondition);
            }
            // 选择筛选条件的动作回调到整个菜单控件的引用者
            if (mCallback != null) {
                mCallback.onSelectCondition(mIndex, conditionId);
            }
        }
    }


    /**
     * 关闭显示的菜单
     */
    public void closeMenu(boolean animation) {
        if (isMenuShow()) {
            mMenuView.closeMenu(animation);
        }
    }

    /**
     * 菜单的选中状态和显示状态重置，并关闭菜单
     */
    public void resetMenu() {
        // 重置菜单
        if (mMenuView != null) {
            mMenuView.resetMenu();
        }
        // 重置标题栏
        if (mShowSelect) {
            mTitleCreator.resetTitle();
        }
        // 关闭菜单
        closeMenu(false);
    }

    /**
     * 菜单是否显示
     */
    public boolean isMenuShow() {
        return mMenuView != null && mMenuView.isShowing();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mMenuDataHelper != null) {
            mMenuDataHelper.onViewDestroy();
            mMenuDataHelper = null;
        }
        mCallback = null;
        if (mMenuView != null) {
            mMenuView.destroy();
            mMenuView = null;
        }
        if (mLifeCycleCallback != null) {
            ((Activity) mContext).getApplication().unregisterActivityLifecycleCallbacks(mLifeCycleCallback);
        }
    }

    /**
     * 是否显示底部横线
     */
    public void setShowLine(boolean showLine) {
        mLineView.setVisibility(showLine ? VISIBLE : GONE);
    }

    /**
     * 设置是否在标题栏显示选择的条目
     */
    public void setShowSelected(boolean showSelected) {
        mShowSelect = showSelected;
    }

    /**
     * 获取菜单数据
     */
    public void requestMenuData() {
        mMenuDataHelper.requestFilterData();
    }

    /**
     * 设置标题条件条目点击的监听
     */
    public void setCallback(FilterMenuViewCallback callback) {
        this.mCallback = callback;
    }

    /**
     * 条件标题条目点击监听
     */
    public interface FilterMenuViewCallback {
        // 点击筛选标题
        void onClickTitleItem(int index, View titleView);

        // 选择条件条目
        void onSelectCondition(int index, Object selectCondition);
    }
}
