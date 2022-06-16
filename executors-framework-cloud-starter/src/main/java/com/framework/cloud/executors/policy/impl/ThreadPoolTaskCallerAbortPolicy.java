package com.framework.cloud.executors.policy.impl;

import com.framework.cloud.executors.policy.AbstractThreadPoolTaskPolicy;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 直接抛出java.util.concurrent.RejectedExecutionException异常
 *
 * @author wusiwei
 */
@Service("abort")
public class ThreadPoolTaskCallerAbortPolicy extends AbstractThreadPoolTaskPolicy {

    @Override
    protected RejectedExecutionHandler policy() {
        return new ThreadPoolExecutor.AbortPolicy();
    }
}
