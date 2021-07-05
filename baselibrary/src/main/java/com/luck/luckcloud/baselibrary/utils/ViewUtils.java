package com.luck.luckcloud.baselibrary.utils;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.TextView;

/**
 * View的工具类
 * Created by fa on 2020/4/12.
 */
public class ViewUtils {
    /**
     * 设置单行显示
     */
    public static void setMaxLineText(TextView textView, String text) {
        if (textView == null) {
            return;
        }
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setText(text);
    }

    /**
     * 设置TextView半加粗，相对textStyle的Bold细一点
     */
    public static void setTextViewMedium(TextView textView) {
        if (textView == null) {
            return;
        }
        textView.getPaint().setFakeBoldText(true);
    }


    /**
     * 添加删除线
     */
    public static void addDelLine(TextView textView) {
        if (textView == null) {
            return;
        }
        textView.getPaint().setFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * 添加下划线
     */
    public static void addBottomLine(TextView textView, boolean add) {
        if (textView == null) {
            return;
        }
        if (add) {
            textView.getPaint().setFlags(0);// 取消再添加
            textView.getPaint().setAntiAlias(true);//抗锯齿
            textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        } else {
            textView.getPaint().setFlags(0);
            textView.getPaint().setAntiAlias(true);//抗锯齿
        }
    }

    /**
     * 获取View的高度
     */
    public static int getViewHeight(View view) {
        if (view == null) {
            return 0;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return view.getMeasuredHeight();
    }

    /**
     * 获取View宽度
     */
    public static int getViewWidth(View view) {
        if (view == null) {
            return 0;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return view.getMeasuredWidth();
    }


    public static void setTextSizePX(TextView textView, int px) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, px);
    }

    public static void setTextSizeSP(TextView textView, int sp) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
    }

    public static void setTextSizeDP(TextView textView, float dp) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dp);
    }


    /**
     * 增加view的触摸范围
     */
    public static void expandViewTouchDelegate(final View view, final int top, final int bottom, final int left, final int right) {
        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);
                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }
}
