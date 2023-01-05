package com.framework.cloud.stream.rabbit;

import com.alibaba.fastjson2.JSONObject;
import com.framework.cloud.stream.message.StreamMessage;
import com.framework.cloud.stream.producer.StreamProducer;
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
public class RabbitProducer implements StreamProducer {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(StreamMessage content, String exchangeName, String routingKey) {
        String messageId = content.messageId();
        String contentJson = JSONObject.toJSONString(content);
        Message message = MessageBuilder
                .withBody(contentJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setCorrelationId(content.messageId())
                .setMessageId(content.messageId())
                .build();
        long expiration = content.expiration();
        if (expiration > 0) {
            if (content.isDelay()) {
                message = MessageBuilder.fromMessage(message).setHeader(MessageProperties.X_DELAY, expiration).build();
            } else {
                message = MessageBuilder.fromMessage(message).setExpiration(String.valueOf(expiration)).build();
            }
        }
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, new CorrelationData(messageId));
    }

}
