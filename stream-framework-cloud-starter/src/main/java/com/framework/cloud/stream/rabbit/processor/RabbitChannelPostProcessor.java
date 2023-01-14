package com.framework.cloud.stream.rabbit.processor;

import com.framework.cloud.stream.utils.MessageHeaderUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;

import java.util.Map;

/**
 * rabbit header post processor
 *
 * @author wusiwei
 */
@SuppressWarnings("all")
public class RabbitChannelPostProcessor implements MessagePostProcessor {

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        putTraceIdInMessageHeader(message);
        return message;
    }

    @Override
    public Message postProcessMessage(Message message, Correlation correlation) {
        putTraceIdInMessageHeader(message);
        return message;
    }

    @Override
    public Message postProcessMessage(Message message, Correlation correlation, String exchange, String routingKey) {
        putTraceIdInMessageHeader(message);
        return message;
    }

    private void putTraceIdInMessageHeader(Message message) {
        MessageProperties properties = message.getMessageProperties();
        properties = properties != null ? properties : new MessageProperties();
        Map<String, Object> headers = properties.getHeaders();
        MessageHeaderUtil.doPreHeaders(headers, properties::setHeader);
    }
}
