package com.framework.cloud.executors.policy.impl;

import com.framework.cloud.executors.policy.AbstractThreadPoolTaskPolicy;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 由调用线程（提交任务的线程）处理该任务，如果调用线程是主线程，那么主线程会调用执行器中的execute方法来执行改任务
 *
 * @author wusiwei
 */
@Service("runs")
public class ThreadPoolTaskCallerRunsPolicy extends AbstractThreadPoolTaskPolicy {

    @Override
    protected RejectedExecutionHandler policy() {
        return new ThreadPoolExecutor.CallerRunsPolicy();
    }
}
