package com.framework.cloud.executors.policy;

import com.framework.cloud.executors.decorator.ContextDecorator;
import com.framework.cloud.executors.enums.RejectedType;
import com.framework.cloud.executors.feature.ExecutorsFeature;
import com.framework.cloud.executors.properties.ExecutorsProperties;
import com.framework.cloud.executors.utils.ExecutorsUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 由调用线程（提交任务的线程）处理该任务，如果调用线程是主线程，那么主线程会调用执行器中的execute方法来执行改任务
 *
 * @author wusiwei
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(ExecutorsProperties.class)
public class ThreadPoolTaskCallerRunsPolicy implements ExecutorsFeature {

    private final ExecutorsProperties executorsProperties;
    private final RequestContextListener requestContextListener;

    @Override
    public ThreadPoolTaskExecutor executor(@NonNull String threadNamePrefix) {
        return executor(threadNamePrefix, executorsProperties.getCorePoolSize(), executorsProperties.getMaxPoolSize(), executorsProperties.getRejectedType());
    }

    @Override
    public ThreadPoolTaskExecutor executor(@NonNull String threadNamePrefix, RejectedType rejectedType) {
        return executor(threadNamePrefix, executorsProperties.getCorePoolSize(), executorsProperties.getMaxPoolSize(), rejectedType);
    }

    @Override
    public ThreadPoolTaskExecutor executor(@NonNull String threadNamePrefix, Integer coreSize, Integer maxSize) {
        return executor(threadNamePrefix, coreSize, maxSize, executorsProperties.getRejectedType());
    }

    @Override
    public ThreadPoolTaskExecutor executor(@NonNull String threadNamePrefix, Integer coreSize, Integer maxSize, RejectedType rejectedType) {
        if (StringUtils.isEmpty(threadNamePrefix)) {
            throw new IllegalThreadStateException("thread name not found");
        }
        if (Objects.isNull(coreSize)) {
            coreSize = executorsProperties.getCorePoolSize();
        }
        coreSize = ExecutorsUtil.corePoolSize(coreSize);
        if (Objects.isNull(maxSize)) {
            maxSize = executorsProperties.getMaxPoolSize();
        }
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池前缀
        executor.setThreadNamePrefix(ExecutorsUtil.threadNamePrefix(threadNamePrefix));
        //核心
        executor.setCorePoolSize(coreSize);
        //最大线程
        executor.setMaxPoolSize(maxSize);
        //队列大小
        executor.setQueueCapacity(executorsProperties.getQueueCapacity());
        //拒绝策略
        executor.setRejectedExecutionHandler(getRejectedExecutionHandler(rejectedType));
        //线程存活时间 秒
        executor.setKeepAliveSeconds(executorsProperties.getKeepAliveSeconds());
        //等待终止 毫秒
        executor.setAwaitTerminationMillis(executorsProperties.getAwaitTerminationMillis());
        //等待任务完成关机
        executor.setWaitForTasksToCompleteOnShutdown(executorsProperties.getWaitForTasksToCompleteOnShutdown());
        //线程任务装饰器
        executor.setTaskDecorator(new ContextDecorator(requestContextListener));
        return executor;
    }

    /**
     * 根据传入的参数获取拒绝策略
     *
     * @param rejectedType 拒绝策略
     * @return RejectedExecutionHandler 实例对象，没有匹配的策略时，默认取 CallerRunsPolicy 实例
     */
    public RejectedExecutionHandler getRejectedExecutionHandler(RejectedType rejectedType) {
        Map<String, RejectedExecutionHandler> rejectedExecutionHandlerMap = new HashMap<>(16);
        rejectedExecutionHandlerMap.put(RejectedType.CallerRunsPolicy.name(), new ThreadPoolExecutor.CallerRunsPolicy());
        rejectedExecutionHandlerMap.put(RejectedType.AbortPolicy.name(), new ThreadPoolExecutor.AbortPolicy());
        rejectedExecutionHandlerMap.put(RejectedType.DiscardPolicy.name(), new ThreadPoolExecutor.DiscardPolicy());
        rejectedExecutionHandlerMap.put(RejectedType.DiscardOldestPolicy.name(), new ThreadPoolExecutor.DiscardOldestPolicy());
        return rejectedExecutionHandlerMap.getOrDefault(rejectedType.name(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
