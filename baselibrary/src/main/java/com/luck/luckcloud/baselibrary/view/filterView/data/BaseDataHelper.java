package com.luck.luckcloud.baselibrary.view.filterView.data;

import android.content.Context;

import com.luck.luckcloud.baselibrary.entity.BaseFilterMenuEntity;

import java.util.List;

/**
 * 筛选的底部菜单的数据帮助类
 * author: fa
 * date: 2018/3/14 09:51.
 */

public abstract class BaseDataHelper {
    private static final String TAG = BaseDataHelper.class.getSimpleName();
    protected Context mContext;
    protected FilterMenuDataCallback mCallback;

    public BaseDataHelper(Context context) {
        this.mContext = context;
    }

    /**
     * 网络数据请求(index 传-1的时候不判断索引)
     */
    public abstract void requestFilterData();


    /**
     * 当整个FilterView销毁的时候回调
     */
    public abstract void onViewDestroy();

    public void setFilterMenuDataCallback(FilterMenuDataCallback callback) {
        this.mCallback = callback;
    }

    /**
     * 数据获取的回调
     */
    public interface FilterMenuDataCallback {
        void onDataSuccess(int index, List<BaseFilterMenuEntity> data);
    }
}
