package com.framework.cloud.stream.configuration;

import com.framework.cloud.executors.feature.ExecutorsFeature;
import com.framework.cloud.stream.constant.StreamConstant;
import com.framework.cloud.stream.producer.StreamProducer;
import com.framework.cloud.stream.rabbit.RabbitProducer;
import com.framework.cloud.stream.rabbit.processor.RabbitChannelPostProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.AsyncTaskExecutor;

/**
 * rabbit
 *
 * @author wusiwei
 */
@EnableConfigurationProperties(RabbitProperties.class)
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.rabbitmq", value = "enable", havingValue = "true")
public class RabbitStreamConfiguration {

    private final ExecutorsFeature executorsFeature;

    @Bean
    public ConnectionFactory connectionFactory(RabbitProperties rabbitProperties) {
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
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.addBeforePublishPostProcessors(new RabbitChannelPostProcessor());
        return rabbitTemplate;
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", value = "type", havingValue = "simple")
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory, RabbitProperties rabbitProperties) {
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
    public DirectRabbitListenerContainerFactory directRabbitListenerContainerFactory(ConnectionFactory connectionFactory, RabbitProperties rabbitProperties) {
        RabbitProperties.DirectContainer direct = rabbitProperties.getListener().getDirect();
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(direct.getAcknowledgeMode());
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public StreamProducer rabbitProducer(RabbitTemplate rabbitTemplate) {
        return new RabbitProducer(rabbitTemplate);
    }

    @Bean(StreamConstant.CONSUME_POOL)
    public AsyncTaskExecutor streamRabbitPool() {
        return executorsFeature.executor(StreamConstant.CONSUME_POOL);
    }
}
