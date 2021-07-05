package com.luck.luckcloud.baselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;

import com.luck.luckcloud.baselibrary.R;
import com.luck.luckcloud.baselibrary.utils.LogUtils;


/**
 * 可以自定义处理超链接点击的TextView
 * 使用时注意必须设置autoLink属性
 * Created by fa on 2019/12/10.
 */

public class CashLinkClickTextView extends AppCompatTextView {
    private static final String TAG = CashLinkClickTextView.class.getSimpleName();

    private Context mContext;
    private boolean mShowUnderLine;
    private int mLinkColor;
    private LinkClickCallback mCallback;

    public void setCallback(LinkClickCallback callback) {
        this.mCallback = callback;
    }

    public CashLinkClickTextView(Context context) {
        this(context, null);
    }

    public CashLinkClickTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CashLinkClickTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttr(attrs);
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.CashLinkClickTextView);
        mShowUnderLine = array.getBoolean(R.styleable.CashLinkClickTextView_showUnderLine, false);
        mLinkColor = array.getResourceId(R.styleable.CashLinkClickTextView_linkColor, 0);
        array.recycle();
    }

    public void setContentText(String content) {
        setText(getClickableHtml(content));
        //这一句很重要，否则ClickableSpan内的onClick方法将无法触发！！
        setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     * 格式化超链接文本内容并设置点击处理
     */
    private CharSequence getClickableHtml(String html) {
        Spanned spannedHtml = Html.fromHtml(html);
        SpannableStringBuilder clickableHtmlBuilder = new SpannableStringBuilder(spannedHtml);
        URLSpan[] urls = clickableHtmlBuilder.getSpans(0, spannedHtml.length(), URLSpan.class);
        for (final URLSpan span : urls) {
            setLinkClickable(clickableHtmlBuilder, span);
        }
        return clickableHtmlBuilder;
    }

    /**
     * 设置点击超链接对应的处理内容
     */
    private void setLinkClickable(final SpannableStringBuilder clickableHtmlBuilder, final URLSpan urlSpan) {
        int start = clickableHtmlBuilder.getSpanStart(urlSpan);
        int end = clickableHtmlBuilder.getSpanEnd(urlSpan);
        int flags = clickableHtmlBuilder.getSpanFlags(urlSpan);
        clickableHtmlBuilder.setSpan(new MyClickSpan(urlSpan), start, end, flags);
    }

    private class MyClickSpan extends ClickableSpan {
        private URLSpan mURLSpan;

        MyClickSpan(URLSpan URLSpan) {
            this.mURLSpan = URLSpan;
        }

        @Override
        public void onClick(View widget) {
            String linkStr = mURLSpan.getURL();
            LogUtils.e(TAG, "获取的链接是：" + linkStr);
            if (mCallback != null) {
                mCallback.onClickLink(linkStr);
            }
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            if (mLinkColor != 0) {
                textPaint.setColor(ContextCompat.getColor(mContext, mLinkColor));// 颜色
            }
            textPaint.setUnderlineText(mShowUnderLine);// 无下划线
        }
    }

    public interface LinkClickCallback {
        void onClickLink(String linkValue);
    }
}
