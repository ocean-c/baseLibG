package com.luck.gaia.baselibrary.http.SSL;


import com.luck.gaia.baselibrary.utils.LogUtils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * author: fa
 * date: 2018/2/28 11:13.
 */
public class TrustAllManager implements X509TrustManager {
    private static final String TAG = TrustAllManager.class.getSimpleName();

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        //do nothing，接受任意客户端证书
        LogUtils.e(TAG, "========X509TrustManager====checkClientTrusted()============>");
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        //do nothing，接受任意服务端证书
        LogUtils.e(TAG, "========X509TrustManager====checkServerTrusted()============>");
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};
    }

}
