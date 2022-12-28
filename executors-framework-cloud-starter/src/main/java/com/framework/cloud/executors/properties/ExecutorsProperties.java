package com.framework.cloud.executors.properties;

import com.framework.cloud.executors.enums.RejectedType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池 配置文件
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "framework.executors")
public class ExecutorsProperties {

    /**
     * 核心线程数量
     */
    private Integer corePoolSize;
    /**
     * 线程池维护线程的最大数量
     */
    private Integer maxPoolSize;
    /**
     * 拒绝策略
     */
    private RejectedType rejectedType = RejectedType.CallerRunsPolicy;
    /**
     * 队列最大长度
     */
    private Integer queueCapacity = 20;
    /**
     * 线程存活时间(单位：秒)
     */
    private Integer keepAliveSeconds = 60;
    /**
     * 等待终止(单位：毫秒)
     */
    private Integer awaitTerminationMillis = 5000;
    /**
     * 是否等待任务完成关机
     */
    private Boolean waitForTasksToCompleteOnShutdown = true;
}
