package com.framework.cloud.executors.policy.impl;

import com.framework.cloud.executors.policy.AbstractThreadPoolTaskPolicy;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 放弃当前任务，并且不会抛出任何异常
 *
 * @author wusiwei
 */
@Service("discard")
public class ThreadPoolTaskCallerDiscardPolicy extends AbstractThreadPoolTaskPolicy {

    @Override
    protected RejectedExecutionHandler policy() {
        return new ThreadPoolExecutor.DiscardPolicy();
    }
}
