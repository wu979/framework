package com.framework.cloud.stream.rabbit.processor;

import com.framework.cloud.holder.constant.HeaderConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;

/**
 * rabbit header post processor
 *
 * @author wusiwei
 */
public class TraceIdMessagePostProcessor implements MessagePostProcessor {

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

    private void putTraceIdInMessageHeader(Message message) {
        String traceId = MDC.get(HeaderConstant.TRACE_ID);
        if (StringUtils.isNotEmpty( traceId)) {
            MessageProperties properties = message.getMessageProperties();
            properties = properties != null ? properties : new MessageProperties();
            properties.setHeader(HeaderConstant.TRACE_ID, traceId);
        }
    }
}
