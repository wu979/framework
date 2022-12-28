package com.framework.cloud.common.utils;

import com.framework.cloud.common.constant.DateConstant;
import com.framework.cloud.common.enums.GlobalNumber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author wusiwei
 */
public class DateUtil {

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return DateConstant.FORMAT_DATE_TIME;
    }

    /**
     * 获得默认的 date pattern
     */
    public static String getDateTimePattern() {
        return DateConstant.FORMAT_DATE_TIME_SHORT;
    }

    /**
     * 根据预设格式返回当前日期
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据用户格式返回当前日期
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }

    /**
     * 根据用户格式返回当前日期
     */
    public static String getDay() {
        return format(new Date(), DateConstant.FORMAT_SHORT);
    }

    /**
     * 根据用户格式返回当前日期
     */
    public static String getDate() {
        return format(new Date(), DateConstant.FORMAT_DATE);
    }

    /**
     * 根据用户格式返回当前月
     */
    public static String getMouth() {
        return format(new Date(), DateConstant.FORMAT_MONTH);
    }

    /**
     * 使用预设格式格式化日期
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }

    /**
     * 使用用户格式格式化日期
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加秒数
     *
     * @param date 日期
     * @param n    要增加的天数
     */
    public static Date addSecond(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, n);
        return cal.getTime();
    }

    /**
     * 获取时间戳
     */
    public static String getTimeMillis() {
        SimpleDateFormat df = new SimpleDateFormat(DateConstant.FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     */
    public static String getYear(Date date) {
        return format(date).substring(GlobalNumber.ZERO.getIntValue(), GlobalNumber.FOUR.getIntValue());
    }

}
