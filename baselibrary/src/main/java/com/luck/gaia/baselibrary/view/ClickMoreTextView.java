package com.luck.gaia.baselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.luck.gaia.baselibrary.R;
import com.luck.gaia.baselibrary.utils.LogUtils;
import com.luck.gaia.baselibrary.utils.StringUtils;

/**
 * 可以展开和收起的显示文字的TextView
 * Created by fa on 2020/5/2.
 */
public class ClickMoreTextView extends AppCompatTextView {
    private static final String TAG = ClickMoreTextView.class.getSimpleName();

    private int mMaxLines = 2;// 最多显示行数
    private String mMoreText = "更多";
    private String mCloseText = "收起";
    private int mClickTextColor;// 可点击的文字颜色

    public ClickMoreTextView(Context context) {
        this(context, null);
    }

    public ClickMoreTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClickMoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(context, attrs);
    }

    /**
     * 初始化属性
     */
    private void initAttribute(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClickMoreTextView);
        mMaxLines = typedArray.getInt(R.styleable.ClickMoreTextView_max_line, mMaxLines);
        String moreText = typedArray.getString(R.styleable.ClickMoreTextView_more_text);
        if (!StringUtils.isEmpty(moreText)) {
            mMoreText = moreText;
        }
        String closeText = typedArray.getString(R.styleable.ClickMoreTextView_close_text);
        if (!StringUtils.isEmpty(closeText)) {
            mCloseText = moreText;
        }
        mClickTextColor = typedArray.getColor(R.styleable.ClickMoreTextView_more_text_color, ContextCompat.getColor(getContext(), R.color.color_4587eb));
        typedArray.recycle();
    }

    /**
     * 根据TextView内容、宽度、显示的最大行数获取可以显示的最后一个字符的下标  否则返回-1
     */
    public static int getLastCharIndexForLimitTextView(TextView textView, String content, int width, int maxLine) {
        LogUtils.e(TAG, "宽度是" + width);
        TextPaint textPaint = textView.getPaint();
        StaticLayout staticLayout = new StaticLayout(content, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        if (staticLayout.getLineCount() > maxLine) {
            return staticLayout.getLineStart(maxLine) - 1;//exceed
        } else {
            return -1;//not exceed the max line
        }
    }

    /**
     * 限制TextView显示字符字符，并且添加showMore和show more的点击事件
     */
    public void setContent(String content, ClickMoreTextViewCallback callback) {
        this.mCallback = callback;
        final long startTime = System.currentTimeMillis();
        //在recyclerView和ListView中，由于复用的原因，这个TextView可能以前就画好了，能获得宽度
        int width = getMeasuredWidth();
        //获取textView的实际宽度，这里可以用各种方式（一般是dp转px写死）填入TextView的宽度
        if (width == 0) {
            width = 1000;
        }
        int lastCharIndex = getLastCharIndexForLimitTextView(this, content, width, mMaxLines);
        LogUtils.e(TAG, "最后一个字符的下标是：" + lastCharIndex);
        //如果行数没超过限制
        if (lastCharIndex < 0) {
            setText(content);
            return;
        }
        //如果超出了行数限制
        //this will deprive the recyclerView's focus
        setMovementMethod(LinkMovementMethod.getInstance());
        //构造spannableString
        String explicitText = null;
        if (content.charAt(lastCharIndex) == '\n' || lastCharIndex > mMoreText.length()) {//manual enter
            // 因为需要填充... 所以多减去3
            explicitText = content.substring(0, lastCharIndex - 3) + "...";
        }
        if (explicitText == null) {
            return;
        }
        int sourceLength = explicitText.length();
        explicitText = explicitText + '\n' + mMoreText;
        String explicitTextAll = content + '\n' + mCloseText;
        final SpannableString mSpan = new SpannableString(explicitText);
        final SpannableString mSpanALL = new SpannableString(explicitTextAll);
        // 收起的点击
        mSpanALL.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(mClickTextColor);
                ds.setAntiAlias(true);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View widget) {
                if (mCallback == null || !mCallback.onClickClose()) {
                    setText(mSpan);
                }
            }
        }, content.length(), explicitTextAll.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 展开的点击
        mSpan.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(mClickTextColor);
                ds.setAntiAlias(true);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View widget) {//"...show more" click event
                if (mCallback == null || !mCallback.onClickMore()) {
                    setText(mSpanALL);
                }
            }
        }, sourceLength, explicitText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置为“显示更多”状态下的TextVie
        setText(mSpan);
        LogUtils.e(TAG, "字符串处理耗时" + (System.currentTimeMillis() - startTime) + " ms");
    }

    private ClickMoreTextViewCallback mCallback;

    public interface ClickMoreTextViewCallback {
        boolean onClickMore();

        boolean onClickClose();
    }
}
