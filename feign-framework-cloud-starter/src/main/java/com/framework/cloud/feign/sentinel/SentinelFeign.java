package com.framework.cloud.feign.sentinel;

import com.framework.cloud.feign.constant.FeignConstant;
import com.framework.cloud.feign.fallback.OverallFallbackFactory;
import feign.Contract;
import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Target;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClientFactoryBean;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 重写Sentinel降级处理器
 *
 * @author wusiwei
 */
public class SentinelFeign {

    public static SentinelFeign.Builder builder() {
        return new SentinelFeign.Builder();
    }

    public static final class Builder extends Feign.Builder
            implements ApplicationContextAware {

        private Contract contract = new Contract.Default();
        private ApplicationContext applicationContext;
        private FeignContext feignContext;

        @Override
        public Feign build() {
            super.invocationHandlerFactory(new InvocationHandlerFactory() {
                @SuppressWarnings({"rawtypes", "unchecked"})
                @SneakyThrows
                @Override
                public InvocationHandler create(Target target,
                                                Map<Method, MethodHandler> dispatch) {
                    GenericApplicationContext context = (GenericApplicationContext) Builder.this.applicationContext;
                    BeanDefinition beanDefinition = context.getBeanDefinition(target.type().getName());
                    FeignClientFactoryBean feignClientFactoryBean = (FeignClientFactoryBean) beanDefinition.getAttribute(FeignConstant.REGISTRAR_FACTORY_BEAN);
                    Class fallback = feignClientFactoryBean.getFallback();
                    Class fallbackFactory = feignClientFactoryBean.getFallbackFactory();
                    String beanName = feignClientFactoryBean.getContextId();
                    if (!StringUtils.hasText(beanName)) {
                        beanName = feignClientFactoryBean.getName();
                    }
                    //fallback 实例
                    Object fallbackInstance;
                    //创建SentinelInvocationHandler
                    SentinelInvocationHandler handler;
                    if (Void.TYPE != fallback) {
                        //默认的
                        fallbackInstance = this.getFromContext(beanName, FeignConstant.FALLBACK, fallback, target.type());
                        handler = new SentinelInvocationHandler(target, dispatch, new FallbackFactory.Default(fallbackInstance));
                    } else if (Void.TYPE != fallbackFactory) {
                        //指定的FallbackFactory
                        FallbackFactory fallbackFactoryInstance = (FallbackFactory) getFromContext(beanName, FeignConstant.FALLBACK_FACTORY, fallbackFactory, FallbackFactory.class);
                        handler = new SentinelInvocationHandler(target, dispatch, fallbackFactoryInstance);
                    } else {
                        //默认的FallbackFactory
                        handler = new SentinelInvocationHandler(target, dispatch, new OverallFallbackFactory(target));
                    }
                    return handler;
                }

                @SuppressWarnings({"rawtypes", "unchecked"})
                private Object getFromContext(String name, String type, Class fallbackType, Class targetType) {
                    Object fallbackInstance = feignContext.getInstance(name, fallbackType);
                    if (fallbackInstance == null) {
                        throw new IllegalStateException(String.format("No %s instance of type %s found for feign client %s", type, fallbackType, name));
                    }
                    if (!targetType.isAssignableFrom(fallbackType)) {
                        throw new IllegalStateException(String.format("Incompatible %s instance. Fallback/fallbackFactory of the %s is not assignable", type, fallbackType));
                    }
                    return fallbackInstance;
                }
            });
            super.contract(new SentinelContractHolder(contract));
            return super.build();
        }

        @Override
        public Feign.Builder invocationHandlerFactory(
                InvocationHandlerFactory invocationHandlerFactory) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Feign.Builder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext)
                throws BeansException {
            this.applicationContext = applicationContext;
            feignContext = this.applicationContext.getBean(FeignContext.class);
        }

        private Object getFieldValue(Object instance, String fieldName) {
            Field field = ReflectionUtils.findField(instance.getClass(), fieldName);
            assert field != null;
            field.setAccessible(true);
            try {
                return field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
