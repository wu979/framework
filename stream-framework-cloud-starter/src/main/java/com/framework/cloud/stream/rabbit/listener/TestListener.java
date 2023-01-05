package com.framework.cloud.stream.rabbit.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 *
 * @author wusiwei
 */
@Component
public class TestListener extends MessageListener<TestMessage> {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "direct.mode", durable = "true", type = "direct"),
            value = @Queue(value = "queue.direct", durable = "true"),
            key = "test.route"
    ))
    @Override
    protected void receiveMessage(Message message, Channel channel) throws IOException {
        super.receiveMessage(message, channel);
    }

    @Override
    protected void execute(Message message, TestMessage content) {
        //business
        int i = 1 /0;
    }

    @Override
    protected void fail(Message message, TestMessage content) {
        //fail invoke persistence
    }
}
