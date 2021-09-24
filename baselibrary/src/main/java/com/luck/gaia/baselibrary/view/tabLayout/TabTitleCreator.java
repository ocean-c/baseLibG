package com.luck.gaia.baselibrary.view.tabLayout;

import android.view.View;

/**
 * 自定义标题的TabLayout的Title建造器
 * Created by fa on 2020/5/2.
 */
public abstract class TabTitleCreator {

    public abstract void setTitle(View titleView, String title);

    public abstract View getTitleView(int index);

}
