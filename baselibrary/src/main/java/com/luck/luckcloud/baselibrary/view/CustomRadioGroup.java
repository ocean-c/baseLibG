package com.luck.luckcloud.baselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/**
 * 自定义的RadioGroup，主要为了使得内部使用嵌套布局的时候RadioButton同样可以响应
 * author: fa
 * date: 2017/8/3 10:42.
 */

public class CustomRadioGroup extends LinearLayout {
    // holds the checked id; the ion is empty by default
    private int mCheckedId = -1;
    // tracks children radio buttons checked state
    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    // when true, mOnCheckedChangeListener discards events
    private boolean mProtectFromCheckedChange = false;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;

    public CustomRadioGroup(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        init();
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mChildOnCheckedChangeListener = new CheckedStateTracker();
        mPassThroughListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(mPassThroughListener);
    }


    @Override
    public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
        // the user listener is delegated to our pass-through listener
        mPassThroughListener.mOnHierarchyChangeListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mCheckedId != -1) {
            mProtectFromCheckedChange = true;
            setCheckedStateForView(mCheckedId, true);
            mProtectFromCheckedChange = false;
            setCheckedId(mCheckedId);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof RadioButton) {
            final RadioButton button = (RadioButton) child;
            if (button.isChecked()) {
                mProtectFromCheckedChange = true;
                if (mCheckedId != -1) {
                    setCheckedStateForView(mCheckedId, false);
                }
                mProtectFromCheckedChange = false;
                setCheckedId(button.getId());
            }
        } else if (child instanceof ViewGroup) {
            final RadioButton button = findRadioButton((ViewGroup) child);
            if (button.isChecked()) {
                mProtectFromCheckedChange = true;
                if (mCheckedId != -1) {
                    setCheckedStateForView(mCheckedId, false);
                }
                mProtectFromCheckedChange = false;
                setCheckedId(button.getId());
            }
        }
        super.addView(child, index, params);
    }

    /**
     * 查找radioButton控件
     */
    public RadioButton findRadioButton(ViewGroup group) {
        RadioButton resBtn = null;
        int len = group.getChildCount();
        for (int i = 0; i < len; i++) {
            if (group.getChildAt(i) instanceof RadioButton) {
                resBtn = (RadioButton) group.getChildAt(i);
            } else if (group.getChildAt(i) instanceof ViewGroup) {
                findRadioButton((ViewGroup) group.getChildAt(i));
            }
        }
        return resBtn;
    }

    /**
     * 根据索引获取RadioGroup中的RadioButton
     */
    public RadioButton getRadioButton(int index) {
        RadioButton radioButton;
        if (getChildAt(index) instanceof ViewGroup) {
            radioButton = findRadioButton((ViewGroup) getChildAt(index));
        } else if (getChildAt(index) instanceof RadioButton) {
            radioButton = (RadioButton) getChildAt(index);
        } else {
            return null;
        }
        return radioButton;
    }

    public void check(int id) {
        // don""t even bother
        if (id != -1 && id == mCheckedId) {
            return;
        }
        if (mCheckedId != -1) {
            setCheckedStateForView(mCheckedId, false);
        }
        if (id != -1) {
            setCheckedStateForView(id, true);
        }
        setCheckedId(id);
    }

    private void setCheckedId(int id) {
        mCheckedId = id;
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId);
        }
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof RadioButton) {
            ((RadioButton) checkedView).setChecked(checked);
        }
    }

    public int getCheckedRadioButtonId() {
        return mCheckedId;
    }

    public void clearCheck() {
        check(-1);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        /**
         * {@inheritDoc}
         */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(int w, int h) {
            super(w, h);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(int w, int h, float initWeight) {
            super(w, h, initWeight);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        /**
         * <p>
         * <p>
         * Fixes the child""s width to
         * <p>
         * {@link ViewGroup.LayoutParams＃WRAP_CONTENT} and the
         * <p>
         * child""s height to
         * <p>
         * {@link ViewGroup.LayoutParams＃WRAP_CONTENT} when not
         * <p>
         * specified in the XML file.
         * <p>
         * </p>
         *
         * @param a          the styled attributes set
         * @param widthAttr  the width attribute to fetch
         * @param heightAttr the height attribute to fetch
         */
        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr,
                                         int heightAttr) {
            if (a.hasValue(widthAttr)) {
                width = a.getLayoutDimension(widthAttr, "layout_width");
            } else {
                width = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
            if (a.hasValue(heightAttr)) {
                height = a.getLayoutDimension(heightAttr, "layout_height");
            } else {
                height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
        }
    }

    /**
     * <p>
     * <p>
     * Interface definition for a callback to be invoked when the checked radio
     * <p>
     * button changed in this group.
     * <p>
     * </p>
     */
    public interface OnCheckedChangeListener {
        /**
         * <p>
         * <p>
         * Called when the checked radio button has changed. When the ion
         * <p>
         * is cleared, checkedId is -1.
         * <p>
         * </p>
         *
         * @param group     the group in which the checked radio button has changed
         * @param checkedId the unique identifier of the newly checked radio button
         */
        public void onCheckedChanged(CustomRadioGroup group, int checkedId);
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // prevents infinite recursion
            if (mProtectFromCheckedChange) {
                return;
            }
            mProtectFromCheckedChange = true;
            if (mCheckedId != -1) {
                setCheckedStateForView(mCheckedId, false);
            }
            mProtectFromCheckedChange = false;
            int id = buttonView.getId();
            setCheckedId(id);
        }
    }

    /**
     * <p>
     * <p>
     * A pass-through listener acts upon the events and dispatches them to
     * <p>
     * another listener. This allows the table layout to set its own internal
     * <p>
     * hierarchy change listener without preventing the user to setup his.
     * <p>
     * </p>
     */
    private class PassThroughHierarchyChangeListener implements OnHierarchyChangeListener {
        private OnHierarchyChangeListener mOnHierarchyChangeListener;

        @Override
        public void onChildViewAdded(View parent, View child) {
            if (parent == CustomRadioGroup.this && child instanceof RadioButton) {
                int id = child.getId();
                // generates an id if it""s missing
                if (id == View.NO_ID) {
                    id = child.hashCode();
                    child.setId(id);
                }
                ((RadioButton) child).setOnCheckedChangeListener(mChildOnCheckedChangeListener);
            } else if (parent == CustomRadioGroup.this && child instanceof ViewGroup) {
                RadioButton btn = findRadioButton((ViewGroup) child);
                int id = btn.getId();
                // generates an id if it""s missing
                if (id == View.NO_ID) {
                    id = btn.hashCode();
                    btn.setId(id);
                }
                btn.setOnCheckedChangeListener(mChildOnCheckedChangeListener);
            }
            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        @Override
        public void onChildViewRemoved(View parent, View child) {
            if (parent == CustomRadioGroup.this && child instanceof RadioButton) {
                ((RadioButton) child).setOnCheckedChangeListener(null);
            } else if (parent == CustomRadioGroup.this && child instanceof ViewGroup) {
                findRadioButton((ViewGroup) child).setOnCheckedChangeListener(null);
            }
            if (mOnHierarchyChangeListener != null) {
                mOnHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }

}
