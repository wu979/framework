package com.framework.cloud.stream.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * stream rabbit request header
 *
 * @author wusiwei
 */
@SuppressWarnings("all")
public class StreamRabbitMqTraceIdChannelInterceptor implements ChannelInterceptor {

    private final Field headerField;

    public StreamRabbitMqTraceIdChannelInterceptor() {
        this.headerField = ReflectionUtils.findField(MessageHeaders.class, "headers");
        this.headerField.setAccessible(true);
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message instanceof ErrorMessage ? message : this.doPreSend(message, channel);
    }

    private Message<?> doPreSend(Message<?> message, MessageChannel channel) {
        Map<String, Object> headersMap = (Map<String, Object>) ReflectionUtils.getField(this.headerField, message.getHeaders());
        Object traceId = headersMap.get("tid");
        if (traceId != null && !StringUtils.isEmpty(traceId.toString())) {
            MDC.put("tid", traceId.toString());
            return message;
        }
        MDC.put("tid", TraceContext.traceId());
        return message;
    }
}
