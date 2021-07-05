package com.luck.luckcloud.baselibrary.http.dialog;

import android.content.DialogInterface;

/**
 * 网络加载的Dialog
 * Created by fa on 2019/2/19.
 */

public interface ILoadingDialog {
    void setOnDismissListener(DialogInterface.OnDismissListener listener);
}
