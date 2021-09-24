package com.luck.gaia.baselibrary.view.navigationbar;

/**
 * 自定义title的规范接口
 * Created by 发 on 2017/5/31.
 */

public interface INavigationBar {

    /**
     * 获取头部布局文件
     *
     * @return
     */
    int bindLayoutResId();


    /**
     * 头部数据和View的绑定
     */
    void applyView();

}
