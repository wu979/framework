package com.framework.cloud.executors.utils;

import cn.hutool.core.util.ObjectUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * @author wusiwei
 */
public class ExecutorsUtil {

    private static final Pattern PATTERN = Pattern.compile("[A-Z]");
    private static final String THREAD_NAME_SUFFIX = "-";

    /**
     * 获取核心线程数
     *
     * @return 核心线程数
     */
    public static Integer corePoolSize(Integer corePoolSize) {
        if (ObjectUtil.isNull(corePoolSize) || corePoolSize == 0) {
            corePoolSize = Runtime.getRuntime().availableProcessors();
        }
        return corePoolSize;
    }

    /**
     * 格式化线程前缀
     *
     * @param threadNamePrefix 前缀
     * @return 前缀
     */
    public static String threadNamePrefix(String threadNamePrefix) {
        StringBuilder builder = new StringBuilder(threadNamePrefix);
        Matcher mc = PATTERN.matcher(threadNamePrefix);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, THREAD_NAME_SUFFIX + mc.group().toLowerCase());
            i++;
        }
        if ('-' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }
}
