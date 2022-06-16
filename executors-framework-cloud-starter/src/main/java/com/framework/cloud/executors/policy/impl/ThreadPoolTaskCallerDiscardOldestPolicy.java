package com.framework.cloud.executors.policy.impl;

import com.framework.cloud.executors.policy.AbstractThreadPoolTaskPolicy;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 会将队列中最早添加的元素移除，再尝试添加，如果失败则按该策略不断重试
 *
 * @author wusiwei
 */
@Service("discardOldest")
public class ThreadPoolTaskCallerDiscardOldestPolicy extends AbstractThreadPoolTaskPolicy {

    @Override
    protected RejectedExecutionHandler policy() {
        return new ThreadPoolExecutor.DiscardOldestPolicy();
    }
}
