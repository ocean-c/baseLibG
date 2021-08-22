package com.luck.luckcloud.framelibrary.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.luckcloud.baselibrary.common.Constants;
import com.luck.luckcloud.baselibrary.view.recyclerView.pullToRefresh.LoadViewCreator;
import com.luck.luckcloud.framelibrary.R;


/**
 * RecyclerView的上拉加载更多
 * Created by fa on 2017/6/6.
 */

public class DefaultLoadView extends LoadViewCreator {
    private static final String TAG = DefaultLoadView.class.getSimpleName();
    // 整体的父布局
    private View mRootView;
    // 加载的图标
    private ImageView mLoadIcon;
    // 文字描述
    private TextView mLoadText;
    // 是否没有更多数据了
    private boolean mShowNoData;
    // 加载的动画
    private ObjectAnimator mAnimator;
    // 黑色字体
    private boolean mShowBlackStyle = false;

    @Override
    public View getLoadView(Context context, ViewGroup parent) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_load_footer, parent, false);
        mLoadIcon = mRootView.findViewById(R.id.tv_load_icon);
        mLoadText = mRootView.findViewById(R.id.tv_load_text);
        if (mShowBlackStyle){
            mLoadText.setTextColor(context.getResources().getColor(R.color.color_black));
        }
        mLoadIcon.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mAnimator = ObjectAnimator.ofFloat(mLoadIcon, "rotation", 0, 359f);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        return mRootView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        // 拉上去了就隐藏文字显示图标
        if (currentDragHeight > 0) {
            mLoadText.setVisibility(View.GONE);
            mLoadIcon.setVisibility(View.VISIBLE);
        }
        float rotate = ((float) currentDragHeight) / refreshViewHeight;
        // 不断下拉的过程中不断的旋转图片
        mLoadIcon.setRotation(rotate * 360);
    }

    @Override
    public void onCancelLoad() {
        if (mShowNoData) {
            mLoadText.setText(R.string.text_load_in_the_end);
        } else {
            mLoadText.setText(R.string.text_load_more);
        }
        mLoadText.setVisibility(View.VISIBLE);
        mLoadIcon.setVisibility(View.GONE);
        mAnimator.cancel();
    }

    @Override
    public void onLoading() {
        mLoadText.setVisibility(View.VISIBLE);
        mLoadText.setText(R.string.text_loading);
        mLoadIcon.setVisibility(View.VISIBLE);
        // 加载的时候不断旋转
        mAnimator.start();
    }

    @Override
    public void onStopLoad() {
        if (mShowNoData) {
            mLoadText.setText(R.string.text_load_in_the_end);
        } else {
            mLoadText.setText(R.string.text_load_more);
        }
        mLoadText.setVisibility(View.VISIBLE);
        mLoadIcon.setVisibility(View.GONE);
        // 停止加载的时候停止动画
        mAnimator.cancel();
    }

    /**
     * 上拉的时候是否还有数据
     */
    @Override
    public void setLoadNoData(boolean isNoData) {
        mShowNoData = isNoData;
        if (mLoadText == null) {
            return;
        }
        mAnimator.cancel();
        if (mShowNoData) {
            mLoadText.setText(R.string.text_load_in_the_end);
        } else {
            mLoadText.setText(R.string.text_load_more);
        }
    }

    @Override
    public void onRelease() {
        if (mLoadIcon != null) {
            mAnimator.cancel();
            mLoadIcon.clearAnimation();
        }
    }

    //设置黑色字体
    public void setBlackStyle() {
        mShowBlackStyle = true;
    }
}
