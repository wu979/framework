package com.framework.cloud.executors.feature;

import lombok.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池接口
 *
 * @author wusiwei
 */
public interface ExecutorsFeature {

    /**
     * 创建线程池
     *
     * @param threadNamePrefix 线程前缀
     * @return 线程池
     */
    ThreadPoolTaskExecutor executor(@NonNull String threadNamePrefix);

    /**
     * 创建线程池
     *
     * @param threadNamePrefix 线程前缀
     * @param coreSize         核心线程数
     * @param maxSize          最大线程数
     * @return 线程池
     */
    ThreadPoolTaskExecutor executor(@NonNull String threadNamePrefix, Integer coreSize, Integer maxSize);

}
