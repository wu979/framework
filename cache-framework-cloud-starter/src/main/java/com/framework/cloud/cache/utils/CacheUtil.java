package com.framework.cloud.cache.utils;

import com.framework.cloud.common.annotation.CacheTarget;
import com.google.common.base.Strings;

/**
 * @author wusiwei
 */
public class CacheUtil {

    /**
     * 是否包含 CacheTarget 注解
     */
    public static boolean isCacheTarget(Object value) {
        return null != value && isCacheTarget(value.getClass());
    }

    /**
     * 是否包含 CacheTarget 注解
     */
    public static boolean isCacheTarget(Class<?> clz) {
        return  null != clz && null != (clz.getAnnotation(CacheTarget.class));
    }

    /**
     * 判断结果是否为空或空的字符串
     */
    public static boolean isNullOrBlank(Object cacheVal) {
        return cacheVal == null || (cacheVal instanceof String && Strings.isNullOrEmpty((String) cacheVal));
    }
}
