package com.luck.gaia.framelibrary.entity;

/**
 * 上传图片的实体
 * Created by fa on 2020/4/24.
 */

public class UploadImageEntity {
    private String imagePath;
    private String compressPath;
    private String imageUrl;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
