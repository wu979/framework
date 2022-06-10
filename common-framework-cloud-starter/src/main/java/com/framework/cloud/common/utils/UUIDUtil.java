package com.framework.cloud.common.utils;

import java.util.UUID;

/**
 * uuid
 *
 * @author wusiwei
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

}
