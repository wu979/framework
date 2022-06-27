package com.framework.cloud.cache.utils;

import com.google.common.base.Strings;

/**
 * @author wusiwei
 */
public class CacheUtil {

    /**
     * 是否对象
     */
    public static <T> boolean isTarget(Object obj) {
        return isTarget(obj.getClass());
    }

    /**
     * 是否对象
     */
    public static <T> boolean isTarget(Class<T> clz) {
        return clz != null && !clz.isPrimitive() && !isPackage(clz);
    }

    /**
     * 判断结果是否为空或空的字符串
     */
    public static boolean isNullOrBlank(Object cacheVal) {
        return cacheVal == null || (cacheVal instanceof String && Strings.isNullOrEmpty((String) cacheVal));
    }

    public static <T> boolean isPackage(Class<T> clz) {
        return String.class.isAssignableFrom(clz) || Long.class.isAssignableFrom(clz) || Double.class.isAssignableFrom(clz)
                || Boolean.class.isAssignableFrom(clz) || Character.class.isAssignableFrom(clz)
                || Byte.class.isAssignableFrom(clz) || Short.class.isAssignableFrom(clz)
                || Integer.class.isAssignableFrom(clz) || Float.class.isAssignableFrom(clz);
    }
}
