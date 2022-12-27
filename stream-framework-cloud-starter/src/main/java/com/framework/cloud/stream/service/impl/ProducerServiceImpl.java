package com.framework.cloud.stream.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONObject;
import com.framework.cloud.stream.constant.StreamConstant;
import com.framework.cloud.stream.model.RabbitMessage;
import com.framework.cloud.stream.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author wusiwei
 */
@AllArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(RabbitMessage content, String exchangeName, String routingKey) {
        String messageId = content.getMessageId();
        String contentJson = JSONObject.toJSONString(content);
        Message message = MessageBuilder
                .withBody(contentJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setCorrelationId(content.getMessageId())
                .setMessageId(content.getMessageId())
                .build();
        Long expiration = content.getExpiration();
        if (ObjectUtil.isNotNull(expiration)) {
            if (content.getIsDelay()) {
                message = MessageBuilder.fromMessage(message).setHeader(MessageProperties.X_DELAY, expiration).build();
            } else {
                message = MessageBuilder.fromMessage(message).setExpiration(String.valueOf(expiration)).build();
            }
        }
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, new CorrelationData(messageId));
    }

    private String getKey(String messageId) {
        return StreamConstant.CONTENT_KEY + messageId;
    }

}
