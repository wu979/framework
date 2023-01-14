package com.framework.cloud.stream.wrapper;

import com.framework.cloud.stream.utils.MessageHeaderUtil;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binding.StreamListenerMessageHandler;
import org.springframework.cloud.stream.config.BinderFactoryAutoConfiguration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolverComposite;
import org.springframework.messaging.handler.invocation.InvocableHandlerMethod;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author wusiwei
 */
@SuppressWarnings("all")
public class StreamListenerHandlerMethodFactory extends DefaultMessageHandlerMethodFactory {

    private final HandlerMethodArgumentResolverComposite argumentResolvers = new HandlerMethodArgumentResolverComposite();

    /**
     * 由于 {@link org.springframework.cloud.stream.config.SmartPayloadArgumentResolver } 只能本包访问 但是又是第一个默认解析器
     * 参考 {@link BinderFactoryAutoConfiguration#messageHandlerMethodFactory 148行 }
     * 解析器执行顺序 {@link HandlerMethodArgumentResolverComposite#getArgumentResolver }
     * 获取
     *  {@link DefaultMessageHandlerMethodFactory#argumentResolvers }
     *  组合解析器的解析器列表
     *  设置为子类
     *  {@link StreamListenerHandlerMethodFactory#argumentResolvers }
     *  属性 赋值给 {@link InvocableHandlerMethod }
     */
    public void afterPropertiesSet(DefaultMessageHandlerMethodFactory messageHandlerMethodFactory) {
        Field argumentResolvers = FieldUtils.getField(DefaultMessageHandlerMethodFactory.class, "argumentResolvers", true);
        HandlerMethodArgumentResolverComposite composite = (HandlerMethodArgumentResolverComposite) ReflectionUtils.getField(argumentResolvers, messageHandlerMethodFactory);
        setArgumentResolvers(composite.getResolvers());
    }

    @Override
    public void setArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        if (argumentResolvers == null) {
            this.argumentResolvers.clear();
            super.setArgumentResolvers(argumentResolvers);
            return;
        }
        this.argumentResolvers.addResolvers(argumentResolvers);
        super.setArgumentResolvers(argumentResolvers);
    }

    @Override
    public InvocableHandlerMethod createInvocableHandlerMethod(Object bean, Method method) {
        InvocableHandlerMethod invocableHandlerMethod = new StreamListenerInvocableHandlerMethod(bean, method);
        invocableHandlerMethod.setMessageMethodArgumentResolvers(argumentResolvers);
        return invocableHandlerMethod;
    }

    public static class StreamListenerInvocableHandlerMethod extends InvocableHandlerMethod {

        private final Field headerField;

        public StreamListenerInvocableHandlerMethod(Object bean, Method method) {
            super(bean, method);
            this.headerField = ReflectionUtils.findField(MessageHeaders.class, "headers");
            this.headerField.setAccessible(true);
        }

        /**
         * {@link StreamListener } 监听消息由 {@link StreamListenerMessageHandler#handleRequestMessage(Message)} 处理
         * 重写父类 {@link InvocableHandlerMethod#invoke(Message, Object...)} 方法 在执行转换器方法前 获取上下文信息
         * invoke 方法使用 {@link HandlerMethodArgumentResolverComposite#resolveArgument } 遍历 参数解析程序 {@link HandlerMethodArgumentResolver }
         * 之所以不能实现
         *      {@link HandlerMethodArgumentResolver } 自定义解析器
         *  是因为
         *      {@link HandlerMethodArgumentResolverComposite#getArgumentResolver } 方法，有解析器的缓存
         *  并不会循环每一个解析器
         */
        @Override
        public Object invoke(Message<?> message, Object... providedArgs) throws Exception {
            doPostReceive(message);
            return super.invoke(message, providedArgs);
        }

        private Message<?> doPostReceive(Message<?> message) {
            Map<String, Object> headers = (Map<String, Object>) ReflectionUtils.getField(this.headerField, message.getHeaders());
            MessageHeaderUtil.doPostHeaders(headers);
            return message;
        }

    }


}
