package com.framework.cloud.stream.service;


import com.framework.cloud.stream.model.RabbitMessage;

/**
 * 消息生产者接口
 *
 * @author wusiwei
 */
public interface ProducerService {

    /**
     * 发送消息 (延迟队列请加上 expiration 单个消息过期时间)
     *
     * @param content      消息体
     * @param exchangeName 交换机
     * @param routingKey   路由键
     */
    void sendMessage(RabbitMessage content, String exchangeName, String routingKey);

}
