package com.luck.gaia.baselibrary.view.filterView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.luck.gaia.baselibrary.R;
import com.luck.gaia.baselibrary.entity.BaseFilterMenuEntity;
import com.luck.gaia.baselibrary.view.filterView.menu.BaseMenuCreator;

import java.util.List;

/**
 * 筛选框下方菜单
 * author: fa
 * date: 2018/3/13 13:45.
 */

public class MenuView extends RelativeLayout {
    private static final String TAG = MenuView.class.getSimpleName();

    private Context mContext;
    // 当前显示的Position
    private int mCurrentShowIndex = -1;
    // 需要显示的菜单的集合
    private SparseArray<BaseMenuCreator> mMenuArrays = new SparseArray<>();
    //    private SparseArray<View> mMenuViewArrays = new SparseArray<>();
    // 菜单容器控件的回调
    private MenuViewCallback mCallback;
    // 菜单父布局，用来占位
    private RelativeLayout mMenuParentLayout;


    public MenuView(Context context) {
        this(context, null);
    }

    public MenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        // 默认隐藏
        setVisibility(GONE);
        // 背景半透明
        setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_half_transparent));
        // 点击关闭菜单
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu(true);
            }
        });
        mMenuParentLayout = new RelativeLayout(mContext);
        mMenuParentLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_white));
        mMenuParentLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mMenuParentLayout);
    }

    /**
     * 设置菜单Creator
     */
    public void setMenuCreators(SparseArray<BaseMenuCreator> arrays) {
        this.mMenuArrays = arrays;
    }

    /**
     * 设置菜单数据
     */
    public void setMenuData(int index, List<BaseFilterMenuEntity> data) {
        BaseMenuCreator creator = mMenuArrays.get(index);
        if (creator != null) {
            creator.refreshViewShow(data);
        }
    }

    /**
     * 菜单重置
     */
    public void resetMenu() {
        if (mMenuArrays == null || mMenuArrays.size() == 0) {
            return;
        }
        for (int i = 0; i < mMenuArrays.size(); i++) {
            BaseMenuCreator menuCreator = mMenuArrays.get(mMenuArrays.keyAt(i));
            menuCreator.resetMenu();
        }
    }

    /**
     * 是否处于可见状态
     */
    public boolean isShowing() {
        return mCurrentShowIndex != -1;
    }

    /**
     * 根据索引显示相应菜单
     */
    public void showMenuByIndex(int index) {
        // 点击的不是当前显示的，切换菜单布局
        if (mCurrentShowIndex != index) {
            setVisibility(VISIBLE);
            setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.filter_menu_mask_anim_in));
            // 将子布局添加进父布局中，添加之前移除原来的
            mMenuParentLayout.removeAllViews();
            mMenuParentLayout.setVisibility(VISIBLE);
            mMenuParentLayout.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.filter_menu_anim_in));
            // 根据索引获取布局View
            View childView = mMenuArrays.get(index).getMenuView();
            if (childView != null) {
                childView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                mMenuParentLayout.addView(childView);
            }
            // 更新当前显示的Menu的索引
            mCurrentShowIndex = index;
            // 菜单展开的事件回调回去
            if (mCallback != null) {
                mCallback.onMenuShow(index);
            }
            mMenuArrays.get(index).onMenuShow();
        } else {// 否则关闭菜单
            closeMenu(true);
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu(boolean animation) {
        if (mCurrentShowIndex != -1) {
            if (animation) {
                mMenuParentLayout.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.filter_menu_anim_out));
                setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.filter_menu_mask_anim_out));
            }
            mMenuParentLayout.setVisibility(GONE);
            setVisibility(GONE);
            // 关闭的时候也回调
            if (mCallback != null) {
                mCallback.onMenuClose(mCurrentShowIndex);
            }
            // 重置索引
            mCurrentShowIndex = -1;
        }
    }

    public void setCallback(MenuViewCallback callback) {
        this.mCallback = callback;
    }

    /**
     * 菜单容器控件的回调接口
     */
    public interface MenuViewCallback {

        // 这里的index是对应上方菜单的索引
        void onMenuShow(int index);

        // 这里的index是对应上方菜单的索引
        void onMenuClose(int index);
    }

    /**
     * 资源释放
     */
    public void destroy() {
        if (mMenuArrays != null) {
            mMenuArrays.clear();
            mMenuArrays = null;
        }
    }
}
