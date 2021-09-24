package com.luck.gaia.baselibrary.view.editText;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.luck.gaia.baselibrary.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 根据多个EditText是否有内容控制Button是否可用的TextWatcher
 * Created by fa on 2018/7/21.
 */

public class MultiEditTextWatcher implements TextWatcher {
    private static final String TAG = MultiEditTextWatcher.class.getSimpleName();
    private View mBtnView;
    private List<EditText> mEtList = new ArrayList<>();
    private ConditionHelper mHelper;

    public void setHelper(ConditionHelper helper) {
        this.mHelper = helper;
    }

    /**
     * 设置需要添加监听的EditText
     */
    public void setEtList(List<EditText> etList) {
        this.mEtList = etList;
        if (mEtList == null) {
            mEtList = new ArrayList<>();
        }
        // 遍历集合，添加监听
        for (EditText editText : mEtList) {
            editText.addTextChangedListener(this);
        }
    }

    /**
     * 设置需要添加监听的EditText
     */
    public void setEditTexts(EditText... etList) {
        this.mEtList = Arrays.asList(etList);
        // 遍历集合，添加监听
        for (EditText editText : mEtList) {
            editText.addTextChangedListener(this);
        }
    }

    public void setBtnView(View btnView) {
        this.mBtnView = btnView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (mBtnView != null) {
            mBtnView.setEnabled(false);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int flag = mEtList.size();
        for (EditText editText : mEtList) {
            String content = editText.getText().toString();
            if (mHelper != null) {
                if (mHelper.notFullEditText(editText, content)) {
                    flag--;
                    break;
                }
            } else {
                if (StringUtils.isEmpty(content)) {
                    flag--;
                    break;
                }
            }
        }
        if (flag == mEtList.size()) {
            mBtnView.setEnabled(true);
        } else {
            mBtnView.setEnabled(false);
        }
    }

    public interface ConditionHelper {
        boolean notFullEditText(EditText editText, String content);
    }

    public void release() {
        for (EditText editText : mEtList) {
            editText.removeTextChangedListener(this);
        }
    }
}
