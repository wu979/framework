package com.framework.cloud.cache.serializer;

import jodd.util.StringPool;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;

/**
 * Adding prefix to user-defined key serialization
 *
 * @author wusiwei
 */
public class StringRedisKeySerializer implements RedisSerializer<String> {

    private final String prefix;

    public StringRedisKeySerializer(String prefix) {
        if (!prefix.endsWith(StringPool.COLON)) {
            prefix = prefix + StringPool.COLON;
        }
        this.prefix = prefix;
    }

    @Override
    public byte[] serialize(String string) throws SerializationException {
        if (string == null) {
            return null;
        }
        String key = prefix + string;
        return key.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        if ((bytes == null || bytes.length == 0)) {
            return null;
        }
        String saveKey = new String(bytes, StandardCharsets.UTF_8);
        int indexOf = saveKey.indexOf(prefix);
        if (indexOf > 0) {
            throw new SerializationException("Could not key prefix");
        } else {
            saveKey = saveKey.substring(indexOf);
        }
        return saveKey;
    }
}
