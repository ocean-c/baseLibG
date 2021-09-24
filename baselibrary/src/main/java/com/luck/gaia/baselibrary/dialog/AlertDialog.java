package com.luck.gaia.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.luck.gaia.baselibrary.R;


/**
 * 自定义的万能Dialog
 * Created by fa on 2017/5/31.
 */

public class AlertDialog extends Dialog {
    private AlertController mAlert;

    private AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }

    /**
     * 通过Dialog利用id对象获取里面的控件
     */
    public <T extends View> T getViewById(int viewId) {
        return mAlert.getViewById(viewId);
    }


    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mAlert.setOnClickListener(viewId, listener);
    }

    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }


    /**
     * Dialog的建造者，通过调用Dialog的装配者和参数对象来完成Dialog的装配
     */
    public static class Builder {

        private final AlertController.AlertParams P;

        public Builder(Context context) {
            this(context, R.style.dialog_default_theme);
        }

        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context, themeResId);
        }

        /**
         * 组装Dialog
         */
        public AlertDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCanceledOnTouchOutside(P.mOutCancelable);
            dialog.setCancelable(P.mCancelable);
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * 显示Dialog
         */
        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }

        /**
         * 设置取消监听
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        /**
         * 设置消失监听
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        /**
         * 设置按键监听
         */
        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        /**
         * 设置Dialog的布局文件ID
         *
         * @param layoutId 布局文件ID
         */
        public Builder setContentView(int layoutId) {
            P.mViewLayoutResId = layoutId;
            return this;
        }


        /**
         * 设置Dialog中相应控件的文本信息
         *
         * @param viewId 控件ID
         * @param text   文本
         */
        public Builder setText(int viewId, CharSequence text) {
            P.mTextArrays.put(viewId, text);
            return this;
        }


        /**
         * 设置Dialog中相应控件的点击事件
         *
         * @param viewId   控件ID
         * @param listener 点击事件
         */
        public Builder setOnClickListener(int viewId, View.OnClickListener listener) {
            P.mListenerArrays.put(viewId, listener);
            return this;
        }


        /**
         * 设置充满屏幕
         */
        public Builder fullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 设置是否在底部
         *
         * @param isAnimation 是否显示动画
         */
        public Builder fromBottom(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimation = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        /**
         * 设置是否在顶部
         *
         * @param isAnimation 是否显示动画
         */
        public Builder fromTop(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimation = R.style.dialog_from_top_anim;
            }
            P.mGravity = Gravity.TOP;
            return this;
        }

        /**
         * 设置是否在右侧
         *
         * @param isAnimation 是否显示动画
         */
        public Builder fromRight(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimation = R.style.dialog_from_right_anim;
            }
            P.mGravity = Gravity.RIGHT;
            return this;
        }

        /**
         * 设置是否在左侧
         *
         * @param isAnimation 是否显示动画
         */
        public Builder fromLeft(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimation = R.style.dialog_from_left_anim;
            }
            P.mGravity = Gravity.LEFT;
            return this;
        }

        /**
         * 设置Dialog的宽高
         *
         * @param width  显示的宽度，默认WRAP_CONTENT
         * @param height 显示的高度，默认WRAP_CONTENT
         */
        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 添加默认缩放动画（缩放）
         */
        public Builder addDefaultScaleAnimation() {
            P.mAnimation = R.style.dialog_scale_anim;
            return this;
        }

        /**
         * 设置动画
         */
        public Builder setAnimation(int animationStyle) {
            P.mAnimation = animationStyle;
            return this;
        }

        /**
         * 设置点击返回按键是否可以取消
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        /**
         * 设置点击阴影的时候是否可以取消
         */
        public Builder setCanceledOnTouchOutside(boolean flag) {
            P.mOutCancelable = flag;
            return this;
        }
    }


    @Override
    public void dismiss() {
//        // 在消失的时候与Activity解绑
//        mActivity.getApplication().unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
        super.dismiss();
    }

//    // 在这里把AlertDialog与Activity的生命周期绑定，以便于控制Dialog在页面pause的时候自动消失，避免抛异常
//    private Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks =
//            new DefaultActivityLifecycleCallbacks() {
//
//                @Override
//                public void onActivityPaused(Activity activity) {
//                    // 页面pause的时候dialog消失
//                    // 由于这个方法会监听所有的Activity，所以这里要做判断
//                    if (activity == mActivity) {
//                        if (isShowing()) {
//                            dismiss();
//                        }
//                    }
//                }
//            };

}
