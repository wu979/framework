package com.framework.cloud.stream.wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.common.base.StreamMessage;
import com.framework.cloud.common.exception.StreamException;
import com.framework.cloud.stream.utils.MessageHeaderUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.SmartMessageConverter;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * stream rabbit request header
 *
 * @author wusiwei
 */
@SuppressWarnings("all")
public class StreamListenerChannelInterceptor implements ChannelInterceptor {

    private final Field headerField;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * {@link org.springframework.cloud.stream.config.ContentTypeConfiguration#configurableCompositeMessageConverter }
     *
     * 参考
     *  {@link org.springframework.cloud.stream.config.SmartPayloadArgumentResolver#resolveArgument(MethodParameter, Message)}  }
     *  构造的 MessageConverter debug时 初始化也是这个Bean
     *
     *
     */
    @Qualifier("integrationArgumentResolverMessageConverter")
    @Resource
    private SmartMessageConverter smartMessageConverter;

    public StreamListenerChannelInterceptor() {
        this.headerField = ReflectionUtils.findField(MessageHeaders.class, "headers");
        this.headerField.setAccessible(true);
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        if (message instanceof ErrorMessage) {
            return message;
        }
        Object payload = smartMessageConverter.fromMessage(message, StreamMessage.class);
        if (!(payload instanceof StreamMessage)) {
            throw new StreamException("The message body must be a BaseMessage subclass");
        }
        return this.doPreSend(message);
    }

    private Message<?> doPreSend(Message<?> message) {
        Map<String, Object> headers = (Map<String, Object>) ReflectionUtils.getField(this.headerField, message.getHeaders());
        MessageHeaderUtil.doPreHeaders(headers, headers::put);
        return message;
    }

}
