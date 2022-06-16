package com.framework.cloud.cache.enums;

/**
 * 锁类型 枚举
 *
 * @author wusiwei
 */
public enum LockTypeEnum {

    /**
     * 默认
     */
    REENTRANT_LOCK,

    /**
     * 读锁
     */
    REENTRANT_READ,

    /**
     * 写锁
     */
    REENTRANT_WRITE
}
