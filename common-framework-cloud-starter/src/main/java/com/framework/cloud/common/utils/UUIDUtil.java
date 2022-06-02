package com.framework.cloud.common.utils;

import java.util.UUID;

/**
 * @program: wsw-starter-cloud
 * @description: uuid
 * @author: wsw
 * @create: 2021-06-08 10:56
 **/
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

}
