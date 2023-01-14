package com.framework.cloud.stream.utils;

import com.framework.cloud.common.base.StreamMessage;
import com.framework.cloud.stream.domain.DomainChannel;

import static com.framework.cloud.stream.constant.StreamConstant.TIMEOUT;
/**
 * @author wusiwei
 */
public class MessageSendUtil {

    public static <C extends DomainChannel, T> boolean send(C channel, StreamMessage<T> streamMessage) {
        return channel.output().send(MessageBuilderUtil.build(streamMessage, channel.OUT()), TIMEOUT);
    }

   public static <C extends DomainChannel, T> boolean send(C channel, StreamMessage<T> streamMessage, long timeout) {
       return channel.output().send(MessageBuilderUtil.build(streamMessage), timeout);
   }

    public static <C extends DomainChannel, T> boolean sendDelay(C channel, StreamMessage<T> streamMessage, long timeout) {
        return channel.output().send(MessageBuilderUtil.build(streamMessage, channel.OUT()), timeout);
    }

    public static <C extends DomainChannel, T> boolean sendDelay(C channel, StreamMessage<T> streamMessage) {
        return channel.output().send(MessageBuilderUtil.build(streamMessage, channel.OUT()), TIMEOUT);
    }

    /**
     * 发布
     *
     * @param channel 通道
     * @param streamMessage 消息体
     * @param delay 延迟时间（毫秒）
     * @param timeout 超时时间（毫秒）
     */
    public static <C extends DomainChannel, T> boolean send(C channel, StreamMessage<T> streamMessage, long delay, long timeout) {
        return channel.output().send(MessageBuilderUtil.build(streamMessage, delay), timeout);
    }

}
