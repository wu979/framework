package com.framework.cloud.stream.extend;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.common.utils.StringUtil;
import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.TokenContextHolder;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.model.LoginTenant;
import com.framework.cloud.holder.model.LoginUser;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.jboss.logging.MDC;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binding.StreamListenerMessageHandler;
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
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author wusiwei
 */
@SuppressWarnings("all")
public class StreamListenerHandlerMethodFactory extends DefaultMessageHandlerMethodFactory {

    private final HandlerMethodArgumentResolverComposite argumentResolvers = new HandlerMethodArgumentResolverComposite();

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
        StreamListenerInvocableHandlerMethod invocableHandlerMethod = new StreamListenerInvocableHandlerMethod(bean, method);
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
         */
        @Override
        public Object invoke(Message<?> message, Object... providedArgs) throws Exception {
            doPostReceive(message);
            return super.invoke(message, providedArgs);
        }

        private Message<?> doPostReceive(Message<?> message) {
            Map<String, Object> headersMap = (Map<String, Object>) ReflectionUtils.getField(this.headerField, message.getHeaders());
            Optional<String> token = converterContent(headersMap.get(HeaderConstant.AUTHORIZATION), String::valueOf);
            if (token.isPresent()) {
                TokenContextHolder.getInstance().setToken(token.get());
            }
            Optional<String> traceId = converterContent(headersMap.get(HeaderConstant.TRACE_ID), String::valueOf);
            if (traceId.isPresent()) {
                MDC.put(HeaderConstant.TRACE_ID, traceId.get());
            }
            Optional<String> user = converterContent(headersMap.get(HeaderConstant.X_USER_HEADER), Base64::decodeStr);
            if (user.isPresent()) {
                UserContextHolder.getInstance().setUser(FastJsonUtil.toJavaObject(user.get(), LoginUser.class));
            }
            Optional<String> tenant = converterContent(headersMap.get(HeaderConstant.X_TENANT_HEADER), Base64::decodeStr);
            if (tenant.isPresent()) {
                TenantContextHolder.getInstance().setTenant(FastJsonUtil.toJavaObject(tenant.get(), LoginTenant.class));
            }
            Optional<String> role = converterContent(headersMap.get(HeaderConstant.X_AUTHORITIES_HEADER), Base64::decodeStr);
            if (role.isPresent()) {
                UserRoleContextHolder.getInstance().setRoleList(Sets.newHashSet(StrUtil.splitTrim(role.get(), ",")));
            }
            return message;
        }

        private <R> Optional<R> converterContent(Object obj, Function<String, R> function) {
            return Optional.ofNullable(obj).filter(Objects::nonNull).map(String::valueOf).filter(StringUtil::isNotEmpty).map(function::apply);
        }
    }


}
