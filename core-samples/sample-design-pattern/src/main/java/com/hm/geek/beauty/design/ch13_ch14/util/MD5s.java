package com.hm.geek.beauty.design.ch13_ch14.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * MD5s.
 * 
 * @author huwenfeng
 */
@Slf4j
public class MD5s {

    /**
     * MD5加码 生成32位md5码
     */
    public static String strToMd5(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            log.error("MD5s#strToMd5 getInstance error", e);
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0 ; i < charArray.length ; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = (md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertFromMd5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0 ; i < a.length ; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

}
