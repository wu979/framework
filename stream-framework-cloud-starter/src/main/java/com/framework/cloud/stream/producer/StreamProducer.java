package com.framework.cloud.stream.producer;

import com.framework.cloud.common.base.RabbitMessage;

/**
 * 消息生产者接口
 *
 * @author wusiwei
 */
public interface StreamProducer {

    /**
     * 发送消息 (延迟队列请加上 expiration 单个消息过期时间)
     *
     * @param content      消息体
     * @param exchangeName 交换机
     * @param routingKey   路由键
     */
    <T> void send(RabbitMessage<T> content, String exchangeName, String routingKey);

}
