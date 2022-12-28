package com.framework.cloud.executors.enums;

import lombok.RequiredArgsConstructor;

/**
 * 拒绝策略 枚举
 *
 * @author wusiwei
 */
@RequiredArgsConstructor
public enum RejectedType {

    CallerRunsPolicy,
    AbortPolicy,
    DiscardPolicy,
    DiscardOldestPolicy

}
