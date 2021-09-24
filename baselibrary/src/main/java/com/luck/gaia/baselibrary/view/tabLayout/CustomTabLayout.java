package com.luck.gaia.baselibrary.view.tabLayout;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * 可设置自定义TitleView的TabLayout
 * Created by fa on 2020/5/2.
 */
public class CustomTabLayout extends TabLayout {

    private Context mContext;
    private TabTitleCreator mTitleCreator;

    public CustomTabLayout(Context context) {
        this(context, null);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    /**
     * 设置标题建造器
     */
    public void setTitleCreator(TabTitleCreator titleCreator) {
        this.mTitleCreator = titleCreator;
    }

    /**
     * 初始化自定义的View
     */
    public void init(String[] titleArray) {
        if (mTitleCreator == null || titleArray == null || titleArray.length == 0) {
            return;
        }
        for (int i = 0; i < titleArray.length; i++) {
            View titleView = mTitleCreator.getTitleView(i);
            mTitleCreator.setTitle(titleView, titleArray[i]);
            Tab tab = getTabAt(i);
            if (tab != null) {
                tab.setCustomView(titleView);
            }
        }
    }

    /**
     * 设置单个标题的内容
     */
    public void setTitle(int index, String title) {
        if (mTitleCreator == null) {
            return;
        }
        Tab tab = getTabAt(index);
        if (tab != null) {
            mTitleCreator.setTitle(tab.getCustomView(), title);
        }
    }
}
