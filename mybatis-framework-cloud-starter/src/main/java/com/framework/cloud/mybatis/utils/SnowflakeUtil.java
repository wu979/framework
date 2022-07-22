package com.framework.cloud.mybatis.utils;

import cn.hutool.core.lang.Snowflake;

/**
 * 临时的雪花ID生成
 *
 * @author wusiwei
 */
public class SnowflakeUtil {

    private final static Snowflake SNOWFLAKE = new Snowflake(getRandom(0, 31), getRandom(0, 31));

    /**
     * 获取下一个 ID
     */
    public static long nextId() {
        return SNOWFLAKE.nextId();
    }


    private static long getRandom(int start, int end) {
        return (long) (Math.random() * (end - start + 1) + start);
    }
}
