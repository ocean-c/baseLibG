package com.luck.gaia.baselibrary.image;

import android.content.Context;
import android.widget.ImageView;

import com.luck.gaia.baselibrary.R;
import com.luck.gaia.baselibrary.image.impl.GlideClient;
import com.luck.gaia.baselibrary.image.interfaces.ImageLoaderClient;

/**
 * 图片加载工具类
 * Created by fa on 2020/6/14.
 */
public class ImageLoader {
    private Context mContext;
    private ImageLoaderClient client = new GlideClient();
    private Object imagePath;
    private int placeHolder = R.color.color_f7f7f7;
    private int imageWidth, imageHeight;

    private ImageLoader(Context context) {
        this.mContext = context;
    }

    public static ImageLoader with(Context context) {
        return new ImageLoader(context);
    }

    public ImageLoader client(ImageLoaderClient client) {
        this.client = client;
        return this;
    }

    public ImageLoader imagePath(Object imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public ImageLoader placeHolder(int placeHolder) {
        this.placeHolder = placeHolder;
        return this;
    }

    public ImageLoader imageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
        return this;
    }

    public ImageLoader imageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
        return this;
    }

    public void into(ImageView imageView) {
        if (imageWidth > 0 && imageHeight > 0) {
            client.showImage(mContext, imagePath, imageView, placeHolder, imageWidth, imageHeight);
        } else {
            client.showImage(mContext, imagePath, imageView, placeHolder);
        }
    }
}
