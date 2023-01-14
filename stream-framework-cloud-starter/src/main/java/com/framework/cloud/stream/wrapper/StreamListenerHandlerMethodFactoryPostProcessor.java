package com.framework.cloud.stream.wrapper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.stream.binding.StreamListenerAnnotationBeanPostProcessor;
import org.springframework.cloud.stream.binding.StreamListenerMessageHandler;
import org.springframework.cloud.stream.config.BinderFactoryAutoConfiguration;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolverComposite;
import org.springframework.messaging.handler.invocation.InvocableHandlerMethod;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

import static org.springframework.integration.context.IntegrationContextUtils.ARGUMENT_RESOLVER_MESSAGE_CONVERTER_BEAN_NAME;

/**
 * 覆盖默认初始化消息处理器工厂 {@link DefaultMessageHandlerMethodFactory }
 *
 * @author wusiwei
 */
@AutoConfigureAfter(BinderFactoryAutoConfiguration.class)
public class StreamListenerHandlerMethodFactoryPostProcessor implements BeanPostProcessor {

    @Qualifier(ARGUMENT_RESOLVER_MESSAGE_CONVERTER_BEAN_NAME)
    @Resource
    private CompositeMessageConverter compositeMessageConverter;

    @Resource
    private Validator validator;

    /**
     * 覆盖Bean {@link BinderFactoryAutoConfiguration#messageHandlerMethodFactory }
     * 使用子类 {@link StreamListenerHandlerMethodFactory } 覆盖 {@link InvocableHandlerMethod#invoke(Message, Object...)}  }
     *
     * 消费执行顺序 ->>>>
     * 1、{@link AbstractMessageHandler#handleMessage }
     * 2、{@link AbstractReplyProducingMessageHandler#handleMessageInternal }
     * 3、{@link StreamListenerMessageHandler#handleRequestMessage  }
     * 在前三步 要么方法final 要么构造不是公开 所以从这三步切入 没有找到方法
     *
     * 4、{@link InvocableHandlerMethod#invoke }
     * 5、{@link InvocableHandlerMethod#getMethodArgumentValues }
     * 6、{@link HandlerMethodArgumentResolverComposite#getArgumentResolver }
     *
     *
     * 7、{@link HandlerMethodArgumentResolver } 继承无效，因为组合器会选择第一个解析器 并缓存
     * 8、{@link BinderFactoryAutoConfiguration#messageHandlerMethodFactory } 初始化时 多个解析器未公开 只能本包访问
     * 9、{@link StreamListenerAnnotationBeanPostProcessor#afterSingletonsInstantiated }
     *        9.1、初始化 {@link InvocableHandlerMethod } {@link StreamListenerMessageHandler }
     *             是通过 {@link DefaultMessageHandlerMethodFactory#createInvocableHandlerMethod } new的方式
     *             同时 {@link DefaultMessageHandlerMethodFactory }
     *             也是 {@link BinderFactoryAutoConfiguration#messageHandlerMethodFactory } 也是new的方式
     *        9.2、所以只能通过继承 {@link DefaultMessageHandlerMethodFactory } 来实现
     *        9.3、并且由于第8步在初始化的时候多个解析器只能本包访问
     *        9.3、so {@link StreamListenerHandlerMethodFactory#afterPropertiesSet() } 反射原Bean的组合解析器
     *
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof DefaultMessageHandlerMethodFactory)) {
            return bean;
        }
        StreamListenerHandlerMethodFactory streamListenerHandlerMethodFactory = new StreamListenerHandlerMethodFactory();
        streamListenerHandlerMethodFactory.setValidator(validator);
        streamListenerHandlerMethodFactory.setMessageConverter(compositeMessageConverter);
        streamListenerHandlerMethodFactory.afterPropertiesSet((DefaultMessageHandlerMethodFactory) bean);
        return streamListenerHandlerMethodFactory;
    }

}
