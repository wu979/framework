package com.framework.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author wusiwei
 */
@Slf4j
public class MD5Util {

    public static final String MD5 = "MD5";

    /**
     * 需要盐值
     *
     * @param password 密码
     * @return 加密后密码
     */
    public static String encodeSalt(String password) {
        String encodePassword = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance(MD5);
            BASE64Encoder base64en = new BASE64Encoder();
            encodePassword = base64en.encode(md5.digest(password.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 error: {}", e.getMessage());
            e.printStackTrace();
        }
        return encodePassword;
    }

    /**
     * 不需要盐值
     *
     * @param password 密码
     * @return 加密后密码
     */
    public static String encode(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(MD5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
