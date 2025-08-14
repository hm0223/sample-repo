package com.hm;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

/**
 * SSL Helper for JSoup.
 *
 * @author huwenfeng
 */
public class SSLHelper {
    /**
     * 获取SSLSocketFactory
     */
    public static SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SSL socket factory", e);
        }
    }
}
