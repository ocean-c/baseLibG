package com.luck.luckcloud.baselibrary.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 避免在图片缩放过程中导致的崩溃
 * author: fa
 * date: 2017/9/15 10:09.
 */

public class HackyViewPager extends ViewPager {
    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 此方法用来捕获图片缩放过程中的索引越界异常
     * java.lang.IllegalArgumentException: pointerIndex out of range
     * 不重写此方法的话，图片缩放的时候会崩溃
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
