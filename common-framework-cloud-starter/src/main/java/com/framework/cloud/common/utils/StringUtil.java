package com.framework.cloud.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * string
 *
 * @author wusiwei
 */
public class StringUtil extends StringUtils {

    private StringUtil() {}

    /**
     * 任意相等
     * @param str 值
     * @param css 比较值
     * @return bool
     */
    public static boolean anyEquals(CharSequence str, CharSequence... css) {
        if (str != null && css != null) {
            for (CharSequence charSequence : css) {
                if (str.equals(charSequence)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 所有不相等
     * @param str 值
     * @param css 比较值
     * @return bool
     */
    public static boolean notEquals(CharSequence str, CharSequence... css) {
        return !anyEquals(str, css);
    }
}
