package com.luck.luckcloud.baselibrary.image.impl;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.luck.luckcloud.baselibrary.image.interfaces.ImageLoaderClient;

/**
 * Glide图片加载
 * Created by fa on 2020/6/14.
 */
public class GlideClient implements ImageLoaderClient {
    @Override
    public void showImage(Context context, Object imagePath, ImageView imageView, int placeHolder) {
        Glide.with(context)
                .load(imagePath)
                .placeholder(placeHolder)
                .into(imageView);
    }

    @Override
    public void showImage(Context context, Object imagePath, ImageView imageView, int placeHolder, int imageWidth, int imageHeight) {
        Glide.with(context)
                .load(imagePath)
                .placeholder(placeHolder)
                .override(imageWidth, imageHeight)
                .into(imageView);
    }
}
