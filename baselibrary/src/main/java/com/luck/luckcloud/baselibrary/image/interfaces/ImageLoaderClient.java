package com.luck.luckcloud.baselibrary.image.interfaces;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片加载Client接口
 * Created by fa on 2020/6/14.
 */
public interface ImageLoaderClient {
    void showImage(Context context, Object imagePath, ImageView imageView, int placeHolder);

    void showImage(Context context, Object imagePath, ImageView imageView, int placeHolder, int imageWidth, int imageHeight);

}
