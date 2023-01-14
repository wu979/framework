package com.framework.cloud.stream.utils;

import com.framework.cloud.common.base.StreamMessage;
import com.framework.cloud.stream.properties.StreamProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import static com.framework.cloud.stream.constant.StreamConstant.HEADER_DELAY;
/**
 * @author wusiwei
 */
@Component
public class MessageBuilderUtil {

    private static StreamProperties streamProperties;

    @Autowired
    public MessageBuilderUtil(StreamProperties streamProperties) {
        MessageBuilderUtil.streamProperties = streamProperties;
    }

    public static <T> Message<StreamMessage<T>> build(StreamMessage<T> message) {
        return MessageBuilder.withPayload(message).build();
    }

    public static <T> Message<StreamMessage<T>> build(StreamMessage<T> message, String channel) {
        return build(message, streamProperties.delay(channel));
    }

    public static <T> Message<StreamMessage<T>> build(StreamMessage<T> message, long delayTime) {
        if (delayTime > 0) {
            MessageHeaderAccessor messageHeaderAccessor = new MessageHeaderAccessor();
            messageHeaderAccessor.setHeader(HEADER_DELAY, delayTime);
            return MessageBuilder.createMessage(message, messageHeaderAccessor.getMessageHeaders());
        }
        return MessageBuilder.withPayload(message).build();
    }
}
