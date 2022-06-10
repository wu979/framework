package com.framework.cloud.common.utils;

import com.framework.cloud.common.constant.DateConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * gson
 *
 * @author wusiwei
 */
public class GsonUtil {

    private static final Gson GSON;
    private static final Gson GSON_NULL;

    private GsonUtil() {}

    static {
        GSON = new GsonBuilder().enableComplexMapKeySerialization()
                .setDateFormat(DateConstant.FORMAT_TIME)
                .disableHtmlEscaping()
                .create();
        GSON_NULL = new GsonBuilder().enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateConstant.FORMAT_TIME)
                .disableHtmlEscaping()
                .create();
    }

    /**
     * 获取解析器
     */
    public static Gson getGson() {
        return GSON;
    }

    /**
     * 获取解析器 有空值解析
     */
    public static Gson getWriteNullGson() {
        return GSON_NULL;
    }

    /**
     * 根据对象返回json  过滤空值字段
     */
    public static String toJsonString(Object object) {
        return GSON.toJson(object);
    }

    /**
     * 根据对象返回json  不过滤空值字段
     */
    public static String toJsonString(Object object, GsonSerializerType ser) {
        if (ser == GsonSerializerType.WriteMapNullValue) {
            return GSON_NULL.toJson(object);
        }
        return GSON.toJson(object);
    }

    /**
     * 将字符串转化对象
     */
    public static <T> T toJavaBean(String json, Class<T> cls) {
        return GSON.fromJson(json, cls);
    }

    /**
     * 将字符串转化集合
     */
    public static <T> List<T> toList(String text, Class<T> cls) {
        return GSON.fromJson(text, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 将字符串转化集合中有Map
     */
    public static <T> List<Map<String, T>> toListMap(String text) {
        return GSON.fromJson(text, new TypeToken<List<Map<String, String>>>() {
        }.getType());
    }

    /**
     * 将字符串转化转Map
     */
    public static <T> Map<String, T> toMap(String text) {
        return GSON.fromJson(text, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    /**
     * 将json转化为对应的实体对象
     * new TypeToken<List<T>>() {}.getType()
     * new TypeToken<Map<String, T>>() {}.getType()
     * new TypeToken<List<Map<String, T>>>() {}.getType()
     */
    private static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    enum GsonSerializerType {

        /**
         * 过滤空字符串
         */
        WriteMapNullValue

    }
}
