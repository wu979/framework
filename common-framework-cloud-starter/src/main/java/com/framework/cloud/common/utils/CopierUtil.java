package com.framework.cloud.common.utils;

import cn.hutool.core.lang.Assert;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象转换
 *
 * @author wusiwei
 */
@SuppressWarnings("all")
public class CopierUtil {

    /**
     * 使用Converter的BeanCopier的缓存
     */
    private static final Map<ClassKey, BeanCopier> BEAN_COPIER_WITH_CONVERTER_CACHE = new ConcurrentHashMap<>();
    /**
     * 不使用Converter的BeanCopier的缓存
     */
    private static final Map<ClassKey, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * 解决包装类和基础类型转换问题的Converter
     */
    public final static CustomConverter CUSTOM_CONVERTER = new CustomConverter();

    private CopierUtil() {
    }


    /**
     * 自定义转换器，解决包装类和基础类型字段无法转换问题
     */
    private static class CustomConverter implements Converter {
        @Override
        public Object convert(Object value, Class target, Object context) {
            return value;
        }
    }

    /**
     * 不使用Converter进行对象copy,无法解决包装类和基础类型转换问题，速度较快
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        Assert.notNull(source, "源对象不能为空");
        Assert.notNull(target, "目标对象不能为空");
        BeanCopier beanCopier = genBeanCopier(source, target);
        if (beanCopier == null) {
            return;
        }
        beanCopier.copy(source, target, null);
    }

    /**
     * 使用内置Converter进行对象copy,解决包装类和基础类型转换问题
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyPropertiesWithConverter(Object source, Object target) {
        Assert.notNull(source, "源对象不能为空");
        Assert.notNull(target, "目标对象不能为空");

        BeanCopier beanCopier = genBeanCopierWithConverter(source, target);
        if (beanCopier == null) {
            return;
        }
        beanCopier.copy(source, target, CUSTOM_CONVERTER);
    }

    /**
     * 使用自定义Converter进行对象copy,解决类型转换问题
     *
     * @param source    源对象
     * @param target    目标对象
     * @param converter Converter
     */
    public static void copyPropertiesWithConverter(Object source, Object target, Converter converter) {
        Assert.notNull(source, "源对象不能为空");
        Assert.notNull(target, "目标对象不能为空");
        Assert.notNull(converter, "Converter不能为空");

        BeanCopier beanCopier = genBeanCopierWithConverter(source, target);
        if (beanCopier == null) {
            return;
        }
        beanCopier.copy(source, target, converter);
    }

    /**
     * 获取使用Converter的BeanCopier
     *
     * @param source 源对象
     * @param target 目标对象
     * @return BeanCopier
     */
    public static BeanCopier genBeanCopierWithConverter(Object source, Object target) {
        Assert.notNull(source, "源对象不能为空");
        Assert.notNull(target, "目标对象不能为空");

        ClassKey classKey = buildClassKey(source, target);
        BeanCopier beanCopier = BEAN_COPIER_WITH_CONVERTER_CACHE.get(classKey);
        if (beanCopier == null) {
            BeanCopier newBeanCopier = BeanCopier.create(classKey.sourceClass, classKey.targetClass, true);
            //经测试computeIfAbsent在并发情况下性能较差，这里使用putIfAbsent替换，实现线程安全
            BeanCopier oldBeanCopier = BEAN_COPIER_WITH_CONVERTER_CACHE.putIfAbsent(classKey, newBeanCopier);
            beanCopier = oldBeanCopier != null ? oldBeanCopier : newBeanCopier;
        }
        return beanCopier;
    }

    /**
     * 获取使用Converter的BeanCopier
     *
     * @param sourceClazz 源对象Class
     * @param targetClazz 目标对象Class
     * @return BeanCopier
     */
    public static BeanCopier genBeanCopierWithConverter(Class<?> sourceClazz, Class<?> targetClazz) {
        Assert.notNull(sourceClazz, "源对象Class不能为空");
        Assert.notNull(targetClazz, "目标对象Class不能为空");

        ClassKey classKey = buildClassKey(sourceClazz, targetClazz);
        BeanCopier beanCopier = BEAN_COPIER_WITH_CONVERTER_CACHE.get(classKey);
        if (beanCopier == null) {
            BeanCopier newBeanCopier = BeanCopier.create(sourceClazz, targetClazz, true);
            //经测试computeIfAbsent在并发情况下性能较差，这里使用putIfAbsent替换，实现线程安全
            BeanCopier oldBeanCopier = BEAN_COPIER_WITH_CONVERTER_CACHE.putIfAbsent(classKey, newBeanCopier);
            beanCopier = oldBeanCopier != null ? oldBeanCopier : newBeanCopier;
        }
        return beanCopier;
    }

    /**
     * 获取不使用Converter的BeanCopier
     *
     * @param source 源对象
     * @param target 目标对象
     * @return BeanCopier
     */
    public static BeanCopier genBeanCopier(Object source, Object target) {
        Assert.notNull(source, "源对象不能为空");
        Assert.notNull(target, "目标对象不能为空");
        ClassKey classKey = buildClassKey(source, target);
        BeanCopier beanCopier = BEAN_COPIER_CACHE.get(classKey);
        if (beanCopier == null) {
            BeanCopier newBeanCopier = BeanCopier.create(classKey.sourceClass, classKey.targetClass, false);
            //经测试computeIfAbsent在并发情况下性能较差，这里使用putIfAbsent替换，实现线程安全
            BeanCopier oldBeanCopier = BEAN_COPIER_CACHE.putIfAbsent(classKey, newBeanCopier);
            beanCopier = oldBeanCopier != null ? oldBeanCopier : newBeanCopier;
        }
        return beanCopier;
    }

    /**
     * 获取不使用Converter的BeanCopier
     *
     * @param sourceClazz 源对象Class
     * @param targetClazz 目标对象Class
     * @return BeanCopier
     */
    public static BeanCopier genBeanCopier(Class<?> sourceClazz, Class<?> targetClazz) {
        Assert.notNull(sourceClazz, "源对象Class不能为空");
        Assert.notNull(targetClazz, "目标对象Class不能为空");

        ClassKey classKey = buildClassKey(sourceClazz, targetClazz);
        BeanCopier beanCopier = BEAN_COPIER_CACHE.get(classKey);
        if (beanCopier == null) {
            BeanCopier newBeanCopier = BeanCopier.create(sourceClazz, targetClazz, false);
            //经测试computeIfAbsent在并发情况下性能较差，这里使用putIfAbsent替换，实现线程安全
            BeanCopier oldBeanCopier = BEAN_COPIER_CACHE.putIfAbsent(classKey, newBeanCopier);
            beanCopier = oldBeanCopier != null ? oldBeanCopier : newBeanCopier;
        }
        return beanCopier;
    }

    /**
     * 构建ClassKey
     *
     * @param sourceClazz 源对象Class
     * @param targetClazz 目标对象Class
     * @return ClassKey
     */
    public static ClassKey buildClassKey(Class<?> sourceClazz, Class<?> targetClazz) {
        if (sourceClazz == null || targetClazz == null) {
            return null;
        }
        //构建缓存键对象
        return new ClassKey(sourceClazz, targetClazz);
    }

    /**
     * 构建ClassKey
     *
     * @param source 源对象
     * @param target 目标对象
     * @return ClassKey
     */
    public static ClassKey buildClassKey(Object source, Object target) {
        if (source == null || target == null) {
            return null;
        }
        Class<?> sourceClazz = source.getClass();
        Class<?> targetClazz = target.getClass();
        //构建缓存键对象
        return new ClassKey(sourceClazz, targetClazz);
    }

    /**
     * 定义缓存BeanCopier的键，避免使用类名字符串直接相加导致性能较慢
     * (暂未找到避免碰撞的组装key的方式，故使用对象hashcode()和equals()解决
     */
    private static class ClassKey {
        private Class<?> sourceClass;
        private Class<?> targetClass;

        public ClassKey(Class<?> sourceClass, Class<?> targetClass) {
            this.sourceClass = sourceClass;
            this.targetClass = targetClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ClassKey classKey = (ClassKey) o;

            if (!Objects.equals(sourceClass, classKey.sourceClass)) {
                return false;
            }
            return Objects.equals(targetClass, classKey.targetClass);
        }

        @Override
        public int hashCode() {
            int result = sourceClass != null ? sourceClass.hashCode() : 0;
            result = 31 * result + (targetClass != null ? targetClass.hashCode() : 0);
            return result;
        }
    }

}
