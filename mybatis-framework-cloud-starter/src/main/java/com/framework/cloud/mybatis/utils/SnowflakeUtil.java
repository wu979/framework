package com.framework.cloud.mybatis.utils;

import com.baomidou.mybatisplus.core.toolkit.Sequence;

/**
 * 临时的雪花ID生成
 *
 * @author wusiwei
 */
public class SnowflakeUtil {

    private final static Sequence SNOWFLAKE = new Sequence(getRandom(1, 31), getRandom(1, 31));

    /**
     * 获取下一个 ID
     */
    public static long nextId() {
        long l = SNOWFLAKE.nextId();
        System.out.println(l);
        return l;
    }


    private static long getRandom(int start, int end) {
        return (long) (Math.random() * (end - start + 1) + start);
    }
}
