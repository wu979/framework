package com.framework.cloud.cache.configuration;

import com.framework.cloud.cache.lock.ZkDistributedLock;
import com.framework.cloud.cache.lock.ZkDistributedLockImpl;
import org.springframework.context.annotation.Bean;

/**
 * Initialize the zookeeper type
 *
 * @author wusiwei
 */
public class ZookeeperConfiguration {

    @Bean
    public ZkDistributedLock zkDistributedLock() {
        return new ZkDistributedLockImpl();
    }
}
