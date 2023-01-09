package com.framework.cloud.mybatis.utils;

import com.baidu.fsg.uid.UidGenerator;

/**
 * 临时的雪花ID生成
 *
 * @author wusiwei
 */
public final class IdUtil {

    protected static UidGenerator uidGenerator;

    public static long getId() {
        return uidGenerator.getUID();
    }

    public static String getIdStr() {
        return Long.toString(uidGenerator.getUID());
    }

    public static String parseId(long id) {
        return uidGenerator.parseUID(id);
    }
}
