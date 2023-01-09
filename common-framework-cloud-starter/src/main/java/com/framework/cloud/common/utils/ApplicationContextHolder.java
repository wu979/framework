package com.framework.cloud.common.utils;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author wusiwei
 */
@Component
@Lazy(value = false)
public class ApplicationContextHolder implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ApplicationContextHolder.beanFactory = beanFactory;
    }

    @SneakyThrows
    public static Object getObject(String id) throws BeansException {
        Object object = null;
        object = beanFactory.getBean(id);
        return object;
    }

    @SneakyThrows
    public static <T> T getObject(Class<T> tClass) throws BeansException {
        return beanFactory.getBean(tClass);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    @SneakyThrows
    public static <T> T getBean(Class<T> tClass) throws BeansException {
        return beanFactory.getBean(tClass);
    }

    @SneakyThrows
    public static <T> T getBean(String name, Class<T> clz) throws BeansException {
        return beanFactory.getBean(name, clz);
    }

    @SneakyThrows
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getAliases(name);
    }

    /**
     * BeanFactory is contains bean
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * check bean singleton or prototype
     */
    @SneakyThrows
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.isSingleton(name);
    }

    @SneakyThrows
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }

    /**
     * register bean to factory
     */
    public static <T> boolean registerBean(String beanName, T bean) {
        beanFactory.registerSingleton(beanName, bean);
        return true;
    }

    /**
     * register bean to factory
     */
    public static <T> boolean destroyBean(String beanName, T bean) {
        beanFactory.destroyBean(beanName, bean);
        return true;
    }

    /**
     * find annotations.
     */
    public static <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        return beanFactory.findAnnotationOnBean(beanName, annotationType);
    }
}
