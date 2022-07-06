package com.framework.cloud.stream;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * domain driven event initialization configuration
 *
 * @author wusiwei
 */
@AllArgsConstructor
@EnableConfigurationProperties(RabbitProperties.class)
public class StreamConfiguration {

    private final RabbitProperties rabbitProperties;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitProperties.getHost());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        connectionFactory.setPublisherConfirmType(rabbitProperties.getPublisherConfirmType());
        connectionFactory.setPublisherReturns(rabbitProperties.isPublisherReturns());
        return connectionFactory;
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", value = "type", havingValue = "simple")
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        RabbitProperties.SimpleContainer simple = rabbitProperties.getListener().getSimple();
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(simple.getAcknowledgeMode());
        factory.setConcurrentConsumers(simple.getConcurrency());
        factory.setMaxConcurrentConsumers(simple.getMaxConcurrency());
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", value = "type", havingValue = "direct")
    public DirectRabbitListenerContainerFactory directRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        RabbitProperties.DirectContainer direct = rabbitProperties.getListener().getDirect();
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(direct.getAcknowledgeMode());
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}