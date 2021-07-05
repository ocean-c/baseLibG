package com.luck.luckcloud.framelibrary.entity;


import com.luck.luckcloud.baselibrary.utils.StringUtils;

/**
 * 版本更新实体
 * Created by fa on 2018/8/10.
 */

public class UpdateEntity {
    /**
     * id复制
     * [int]	是	编码id
     * appCode
     * [string]	是	app编码
     * version
     * [string]	是	版本号 v1.0.0
     * updateDesc
     * [string]	是	更新说明
     * updateUrl
     * [string]	是	更新url
     * forceFlag
     * [string]	是	强制更新标识 0 否 1是
     * createTime
     * [datetime]	是	创建时间
     * updateTime
     * [datetime]		更新时间
     */

    private String versionDes;// 新版本描述
    private String version;// 新的版本号
    private String updateUrl;// APK下载链接
    private boolean isForce = false; // 强制更新

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean force) {
        isForce = force;
    }

    /**
     * 是否强制更新
     */
    public boolean isForceUpdate() {
        return isForce;
    }

    public String getVersion() {
        version = StringUtils.isEmpty(version) ? "" : version;
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return updateUrl;
    }

    public void setUrl(String url) {
        this.updateUrl = url;
    }

    public String getVersionDesc() {
        versionDes = StringUtils.isEmpty(versionDes) ? "发现新版本" : versionDes;
        return versionDes;
    }

    public void setVersionDesc(String versionDes) {
        this.versionDes = versionDes;
    }
}
