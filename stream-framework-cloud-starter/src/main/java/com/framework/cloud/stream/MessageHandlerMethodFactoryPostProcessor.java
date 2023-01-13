package com.framework.cloud.stream;

import com.framework.cloud.stream.extend.StreamListenerHandlerMethodFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.stream.config.BinderFactoryAutoConfiguration;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.invocation.InvocableHandlerMethod;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

import static org.springframework.integration.context.IntegrationContextUtils.ARGUMENT_RESOLVER_MESSAGE_CONVERTER_BEAN_NAME;

/**
 * 覆盖默认初始化消息处理器工厂 {@link DefaultMessageHandlerMethodFactory }
 * 使用继承类 {@link StreamListenerHandlerMethodFactory } 代替 使其覆盖 {@link InvocableHandlerMethod#invoke(Message, Object...)}  }
 *
 * @author wusiwei
 */
@AutoConfigureAfter(BinderFactoryAutoConfiguration.class)
public class MessageHandlerMethodFactoryPostProcessor implements BeanPostProcessor {

    @Qualifier(ARGUMENT_RESOLVER_MESSAGE_CONVERTER_BEAN_NAME)
    @Resource
    private CompositeMessageConverter compositeMessageConverter;

    @Resource
    private Validator validator;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DefaultMessageHandlerMethodFactory) {
            StreamListenerHandlerMethodFactory streamListenerHandlerMethodFactory = new StreamListenerHandlerMethodFactory();
            streamListenerHandlerMethodFactory.setValidator(validator);
            streamListenerHandlerMethodFactory.setMessageConverter(compositeMessageConverter);
            streamListenerHandlerMethodFactory.afterPropertiesSet((DefaultMessageHandlerMethodFactory) bean);
            return streamListenerHandlerMethodFactory;
        }
        return bean;
    }

}
