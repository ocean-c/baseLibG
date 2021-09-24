package com.luck.gaia.framelibrary.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.gaia.baselibrary.view.recyclerView.pullToRefresh.RefreshViewCreator;
import com.luck.gaia.framelibrary.R;


/**
 * RecyclerView的上拉加载更多
 * Created by fa on 2017/6/6.
 */

public class DefaultRefreshView extends RefreshViewCreator {
    private static final String TAG = DefaultRefreshView.class.getSimpleName();

    public DefaultRefreshView() {
    }

    // 加载数据的ImageView
    private ImageView mRefreshIcon;
    private TextView mRefreshDesc;
    // 加载的动画
    private ObjectAnimator mAnimator;
    // 黑色字体
    private boolean mShowBlackStyle = false;

    protected int getLayoutId() {
        return R.layout.view_refresh_header;
    }

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        mRefreshIcon = refreshView.findViewById(R.id.iv_refresh_icon);
        mRefreshDesc = refreshView.findViewById(R.id.tv_refresh_desc);
        if (mShowBlackStyle){
            mRefreshDesc.setTextColor(context.getResources().getColor(R.color.color_black));
        }
        mRefreshIcon.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mAnimator = ObjectAnimator.ofFloat(mRefreshIcon, "rotation", 0, 359f);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        return refreshView;
    }

    /*@param currentDragHeight    当前拖动的高度
     * @param refreshViewHeight    总的刷新控件高度
     * @param currentRefreshStatus 当前状态*/
    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        float rotate = ((float) currentDragHeight) / refreshViewHeight;
        // 不断下拉的过程中不断的旋转图片
        mRefreshIcon.setRotation(rotate * 360);
        if (currentDragHeight >= refreshViewHeight) {
            mRefreshDesc.setText(R.string.text_load_release);
        } else {
            mRefreshDesc.setText(R.string.text_load_pull);
        }
    }

    @Override
    public void onCancelRefresh() {
        mRefreshDesc.setText(R.string.text_load_pull);
        mAnimator.cancel();
    }

    @Override
    public void onRefreshing() {
        mRefreshDesc.setText(R.string.text_refreshing);
        // 加载的时候不断旋转
        mAnimator.start();
    }

    @Override
    public void onStopRefresh() {
        mRefreshDesc.setText(R.string.text_refresh_done);
        mAnimator.cancel();
    }

    @Override
    public void onRelease() {
        if (mRefreshIcon != null) {
            mAnimator.cancel();
            mRefreshIcon.clearAnimation();
        }
    }

    //设置黑色字体
    public void setBlackStyle() {
        mShowBlackStyle = true;
    }
}
