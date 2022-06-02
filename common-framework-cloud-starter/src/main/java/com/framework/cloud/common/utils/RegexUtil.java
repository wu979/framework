package com.framework.cloud.common.utils;

import java.text.MessageFormat;
import java.util.regex.Pattern;

/**
 * @program: wsw-starter-cloud
 * @description: 正则工具类
 * @author: wsw
 * @create: 2021-09-07 15:30
 **/
public class RegexUtil {

    /**
     * 正则表达式：验证令牌
     */
    public static final String REGEX_TOKEN = "^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$";

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：是否包含某字符
     */
    public static final String REGEX_CONTAIN = "^.*({0}).*$";

    /**
     * 正则表达式：是否已某字符开头
     */
    public static final String REGEX_STARTS_WITH = "^{0}.*";

    /**
     * 校验用户名
     */
    public static boolean checkUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     */
    public static boolean checkPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     */
    public static boolean checkMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     */
    public static boolean checkEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     */
    public static boolean checkChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     */
    public static boolean checkIdCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     */
    public static boolean checkUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     */
    public static boolean checkIpAddress(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 效验是否包含字符
     *
     * @param str 字符串
     * @param cr  字符
     */
    public static boolean checkContain(String str, String cr) {
        return Pattern.matches(MessageFormat.format(REGEX_CONTAIN, cr), str);
    }

    /**
     * 效验是否已某字符开头
     *
     * @param str 字符串
     * @param cr  字符
     */
    public static boolean checkStartsWith(String str, String cr) {
        return Pattern.matches(MessageFormat.format(REGEX_STARTS_WITH, cr), str);
    }

}
