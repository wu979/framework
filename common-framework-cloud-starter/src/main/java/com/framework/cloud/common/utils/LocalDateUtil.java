package com.framework.cloud.common.utils;

import com.framework.cloud.common.constant.DateConstant;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author wusiwei
 */
public class LocalDateUtil {

    public static final String ZONE = "+8";

    /**
     * 获取秒级
     */
    public static long getTimeSeconds() {
        return getTimeSeconds(LocalDateTime.now());
    }

    /**
     * 获取秒级
     */
    public static long getTimeSeconds(LocalDateTime date) {
        return date.toEpochSecond(ZoneOffset.of(ZONE));
    }

    /**
     * 获取毫秒级
     */
    public static long getTimeMillis() {
        return getTimeMillis(LocalDateTime.now());
    }

    /**
     * 获取毫秒级
     */
    public static long getTimeMillis(LocalDateTime date) {
        return date.toInstant(ZoneOffset.of(ZONE)).toEpochMilli();
    }

    /**
     * 获取当前系统时间
     */
    public static LocalTime getLocalTime() {
        return LocalTime.now();
    }

    /**
     * 获取当前系统日期
     */
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前系统日期时间
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 格式化时间
     *
     * @param dateTime 时间
     * @return 格式化后的时间
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DateConstant.FORMAT_DATE_TIME);
    }

    /**
     * Date转LocalDateTime
     *
     * @param date 日期
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * Date转LocalDate
     *
     * @param date 日期
     */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * Date转LocalDate
     */
    public static LocalTime date2LocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalTime();
    }

    /**
     * 开始时间是否在结束时间之前
     */
    public static boolean isAfter(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.isAfter(endTime);
    }

    /**
     * 开始时间是否在结束时间之后
     */
    public static boolean isBefore(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.isBefore(endTime);
    }

    /**
     * 当前时间 向后推 N分钟
     */
    public static LocalDateTime plusMinutes(long minutes) {
        return plusMinutes(LocalDateTime.now(), minutes);
    }

    /**
     * 指定时间 向后推 N分钟
     */
    public static LocalDateTime plusMinutes(LocalDateTime now, long minutes) {
        return now.plusMinutes(minutes);
    }


    /**
     * 格式化时间 默认yyyy-MM-dd HH:mm:ss格式
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        if (pattern == null || pattern.isEmpty()) {
            pattern = DateConstant.FORMAT_DATE_TIME;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取指定时间到第二天凌晨（00:00）的秒数
     */
    public static long getToMorrowSeconds(Date time) {
        LocalDateTime currTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
        LocalDateTime morrowTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return ChronoUnit.SECONDS.between(currTime, morrowTime);
    }

    /**
     * 获取当前时间到第二天凌晨（00:00）的秒数
     */
    public static long getToMorrowSeconds() {
        return getToMorrowSeconds(new Date());
    }

    /**
     * 获取本月最后一天
     *
     * @return 最后一天 23：59：59
     */
    public static LocalDateTime getLastDay() {
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth())), LocalTime.MAX);
    }

    /**
     * 计算时间差
     * <p>
     * long days = duration.toDays(); //相差的天数
     * long hours = duration.toHours();//相差的小时数
     * long minutes = duration.toMinutes();//相差的分钟数
     * long millis = duration.toMillis();//相差毫秒数
     * long nanos = duration.toNanos();//相差的纳秒数
     */
    public static Duration between(LocalDateTime end) {
        return between(LocalDateTime.now(), end);
    }

    /**
     * 计算时间差
     * <p>
     * long days = duration.toDays(); //相差的天数
     * long hours = duration.toHours();//相差的小时数
     * long minutes = duration.toMinutes();//相差的分钟数
     * long millis = duration.toMillis();//相差毫秒数
     * long nanos = duration.toNanos();//相差的纳秒数
     */
    public static Duration between(LocalDateTime now, LocalDateTime end) {
        return Duration.between(now, end);
    }
}
