package com.framework.cloud.stream.domain;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wusiwei
 */
public interface DomainChannel {

    default String OUT() {
        return "";
    }

    default String IN() {
        return "";
    }

    MessageChannel output();

    SubscribableChannel input();
}
