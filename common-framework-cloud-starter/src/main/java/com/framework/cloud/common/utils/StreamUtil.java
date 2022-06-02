package com.framework.cloud.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 2022-05-20
 *
 * @author wusiwei
 * @version V1.0
 * @description: 流表达式工具类
 */
@UtilityClass
@SuppressWarnings({"varargs"})
public class StreamUtil {

    /**
     * 集合转流 并添加过滤
     */
    @SafeVarargs
    public <E> List<E> filter(Collection<E> list, Predicate<E>... filters) {
        Stream<E> stream = list.stream();
        for (Predicate<E> filter : filters) {
            stream = stream.filter(filter);
        }
        return stream.collect(Collectors.toList());
    }

    /**
     * 过滤集合
     */
    @SafeVarargs
    public <E> List<E> toList(Collection<E> list, Predicate<E>... filters) {
        return executeFilter(list, filters).collect(Collectors.toList());
    }

    /**
     * 过滤集合
     */
    @SafeVarargs
    public <E> Set<E> toSet(Collection<E> list, Predicate<E>... filters) {
        return executeFilter(list, filters).collect(Collectors.toSet());
    }

    /**
     * 抽取集合中某个属性
     */
    @SafeVarargs
    public <E, R> List<R> mapList(Collection<E> list, Function<E, R> mapper, Predicate<E>... filters) {
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return executeFilter(list, filters).map(mapper).filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    /**
     * 抽取集合中某个属性
     */
    @SafeVarargs
    public <E, R> Set<R> mapSet(Collection<E> list, Function<E, R> mapper, Predicate<E>... filters) {
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptySet();
        }
        return executeFilter(list, filters).map(mapper).filter(Objects::nonNull).distinct().collect(Collectors.toSet());
    }

    /**
     * 集合转为为键值对
     *
     * ObjectUtils::firstNonNull 重复 取第一条
     */
    @SafeVarargs
    public <E, K, V> Map<K, V> toMap(Collection<E> list, Function<E, K> keyMapper, Function<E, V> valueMapper, Predicate<E>... filters) {
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return executeFilter(list, filters).collect(Collectors.toMap(keyMapper, valueMapper, ObjectUtils::firstNonNull));
    }

    /**
     * 集合分组
     */
    public <E, K> Map<K, List<E>> groupBy(Collection<E> list, Function<E, K> groupKey) {
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.groupingBy(groupKey));
    }

    /**
     * 集合分组并转换为指定类型
     */
    public <E, K, R> Map<K, List<R>> groupBy(Collection<E> list, Function<E, K> groupKey, Function<List<E>, List<R>> downstream) {
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.groupingBy(groupKey, Collectors.mapping(Function.identity(), Collectors.collectingAndThen(Collectors.toList(), downstream))));
    }

    /**
     * 集合分组并转换为指定类型
     */
    public <E, K, R> Map<K, List<R>> groupBy(Collection<E> list, Function<E, K> groupKey, Supplier<Map<K, List<R>>> mapFactory, Function<List<E>, List<R>> downstream) {
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.groupingBy(groupKey, mapFactory, Collectors.mapping(Function.identity(), Collectors.collectingAndThen(Collectors.toList(), downstream))));
    }

    /**
     * 集合转流 并添加过滤
     */
    @SafeVarargs
    private <E> Stream<E> executeFilter(Collection<E> list, Predicate<E>... filters) {
        Stream<E> stream = list.stream();
        for (Predicate<E> filter : filters) {
            stream = stream.filter(filter);
        }
        return stream;
    }
}
