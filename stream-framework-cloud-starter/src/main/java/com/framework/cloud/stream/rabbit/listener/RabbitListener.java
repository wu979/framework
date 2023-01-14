package com.framework.cloud.stream.rabbit.listener;

import com.framework.cloud.common.base.RabbitMessage;
import com.framework.cloud.stream.enums.PersistenceType;
import org.springframework.amqp.core.Message;

/**
 * @author wusiwei
 */
public interface RabbitListener<T> {

    /**
     * execute
     */
    void consumeMessage(Message message, RabbitMessage<T> rabbitMessage);

    /**
     * execution failed
     */
    void fail(Message message, RabbitMessage<T> rabbitMessage);

    /**
     * execution failed
     */
    void fail(PersistenceType persistenceType, Message message, RabbitMessage<T> rabbitMessage);


}
