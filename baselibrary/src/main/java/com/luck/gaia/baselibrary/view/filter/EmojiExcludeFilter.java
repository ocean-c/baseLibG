package com.luck.gaia.baselibrary.view.filter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Emoji表情过滤
 * Created by fa on 2020/5/30.
 */
public class EmojiExcludeFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; i++) {
            int type = Character.getType(source.charAt(i));
            if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                return "";
            }
        }
        return null;
    }
}
