package com.framework.cloud.common.utils;

import com.alibaba.fastjson2.*;
import com.alibaba.fastjson2.reader.ObjectReaderProvider;
import com.alibaba.fastjson2.util.ParameterizedTypeImpl;
import com.alibaba.fastjson2.util.TypeUtils;
import com.framework.cloud.common.constant.DateConstant;
import lombok.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

/**
 * @author wusiwei
 */
public class FastJsonUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateConstant.FORMAT_DATE_TIME);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DateConstant.FORMAT_SHORT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(DateConstant.FORMAT_TIME);
    private static final ThreadLocal<SimpleDateFormat> LOCAL_DATE_FORMATTER
            = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateConstant.FORMAT_DATE_TIME));

    private static final Function<LocalDateTime, String> dateTimeSerializer
            = DATE_TIME_FORMATTER::format;
    private static final Function<LocalDate, String> dateSerializer
            = DATE_FORMATTER::format;
    private static final Function<LocalTime, String> timeSerializer
            = TIME_FORMATTER::format;
    private static final Function<Date, String> __dateSerializer
            = json -> LOCAL_DATE_FORMATTER.get().format(json);

    private static final Function<String, LocalDateTime> dateTimeDeserializer
            = json -> LocalDateTime.parse(json, DATE_TIME_FORMATTER);
    private static final Function<String, LocalDate> dateDeserializer
            = json -> LocalDate.parse(json, DATE_FORMATTER);
    private static final Function<String, LocalTime> timeDeserializer
            = json -> LocalTime.parse(json, TIME_FORMATTER);
    private static final Function<String, Date> __dateDeserializer
            = json -> {
        try {
            return LOCAL_DATE_FORMATTER.get().parse(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    private static final ObjectReaderProvider provider;

    static {
        provider = JSONFactory.getDefaultObjectReaderProvider();
        provider.registerTypeConvert(String.class, Date.class, __dateDeserializer);
        provider.registerTypeConvert(String.class, LocalDateTime.class, dateTimeDeserializer);
        provider.registerTypeConvert(String.class, LocalDate.class, dateDeserializer);
        provider.registerTypeConvert(String.class, LocalTime.class, timeDeserializer);
        provider.registerTypeConvert(Date.class, String.class, __dateSerializer);
        provider.registerTypeConvert(LocalDateTime.class, String.class, dateTimeSerializer);
        provider.registerTypeConvert(LocalDate.class, String.class, dateSerializer);
        provider.registerTypeConvert(LocalTime.class, String.class, timeSerializer);
    }

    public static ParameterizedType makeJavaType(Type rawType, Type... typeArguments) {
        return new ParameterizedTypeImpl(typeArguments, null, rawType);
    }

    public static String toString(Object value) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return toJSONString(value);
    }

    public static String toJSONString(Object value) {
        return JSON.toJSONString(value);
    }

    public static String toPrettyString(Object value) {
        return toJSONString(value, JSONWriter.Feature.PrettyFormat);
    }

    public static String toJSONString(Object value, JSONWriter.Feature feature) {
        return JSON.toJSONString(value, feature);
    }

    public static Object fromJavaObject(Object value) {
        Object result = null;
        if (Objects.nonNull(value) && (value instanceof String)) {
            result = parseObject((String) value);
        } else {
            result = JSON.toJSON(value);
        }
        return result;
    }

    public static Object parseObject(String content) {
        return JSON.parseObject(content, Object.class);
    }

    public static Object getJsonElement(JSONObject node, String name) {
        return node.get(name);
    }

    public static Object getJsonElement(JSONArray node, int index) {
        return node.get(index);
    }

    public static <T> T toJavaObject(Object node, Class<T> clazz) {
        return JSON.to(clazz, node);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toJavaObject(@NonNull Object node, Type type) {
        if (node instanceof JSONObject) {
            return ((JSONObject) node).to(type);
        }
        if (node instanceof JSONArray) {
            return ((JSONArray) node).to(type);
        }
        if (node instanceof String) {
            return JSON.parseObject((String) node, type);
        }
        return (T) TypeUtils.cast(node, TypeUtils.getClass(type));
    }

    @SuppressWarnings("unchecked")
    public static <T> T toJavaObject(@NonNull Object node, TypeReference<T> typeReference) {
        if (node instanceof JSONObject) {
            return typeReference.to((JSONObject) node);
        }
        if (node instanceof JSONArray) {
            return typeReference.to((JSONArray) node);
        }
        if (node instanceof String) {
            return JSON.parseObject((String) node, typeReference);
        }
        return (T) TypeUtils.cast(node, typeReference.getRawType());
    }

    public static <E> List<E> toJavaList(Object node, Class<E> clazz) {
        return toJavaObject(node, makeJavaType(List.class, clazz));
    }

    public static <E> Set<E> toJavaSet(Object node, Class<E> clazz) {
        return toJavaObject(node, makeJavaType(Set.class, clazz));
    }

    public static List<Object> toJavaList(Object node) {
        return toJavaObject(node, new TypeReference<List<Object>>() {
        });
    }

    public static <V> Map<String, V> toJavaMap(Object node, Class<V> clazz) {
        return toJavaObject(node, makeJavaType(Map.class, String.class, clazz));
    }

    public static Map<String, Object> toJavaMap(Object node) {
        return toJavaObject(node, new TypeReference<Map<String, Object>>() {
        });
    }

    public static <T> T toJavaObject(String content, Class<T> clazz) {
        return JSON.parseObject(content, clazz);
    }

    public static <T> T toJavaObject(String content, Type type) {
        return JSON.parseObject(content, type);
    }

    public static <T> T toJavaObject(String content, TypeReference<T> typeReference) {
        return JSON.parseObject(content, typeReference);
    }

    public static <E> List<E> toJavaList(String content, Class<E> clazz) {
        return JSON.parseObject(content, makeJavaType(List.class, clazz));
    }

    public static List<Object> toJavaList(String content) {
        return JSON.parseObject(content, new TypeReference<List<Object>>() {
        });
    }

    public static <V> Map<String, V> toJavaMap(String content, Class<V> clazz) {
        return JSON.parseObject(content, makeJavaType(Map.class, String.class, clazz));
    }

    public static Map<String, Object> toJavaMap(String content) {
        return JSON.parseObject(content, new TypeReference<Map<String, Object>>() {
        });
    }

}
