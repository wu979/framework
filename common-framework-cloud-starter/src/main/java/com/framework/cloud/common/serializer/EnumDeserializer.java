package com.framework.cloud.common.serializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.framework.cloud.common.base.BaseEnum;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.Map;

/**
 * @author wusiwei
 */
public class EnumDeserializer extends JsonDeserializer<Enum> implements ContextualDeserializer {
    private static final Map<Class, EnumDeserializer> DESERIALIZER_MAP = Maps.newHashMap();
    private Class<? extends Enum> typeClass;

    @Override
    public Enum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (BaseEnum.class.isAssignableFrom(typeClass)) {
            for (Enum item : typeClass.getEnumConstants()) {
                if (jsonParser.getValueAsString().equals(((BaseEnum) item).getValue().toString())) {
                    return item;
                }
            }
        }
        return Enum.valueOf(typeClass, jsonParser.getText());
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        EnumDeserializer deserializer = DESERIALIZER_MAP.get(deserializationContext.getContextualType().getRawClass());
        if (deserializer == null) {
            synchronized (this) {
                deserializer = DESERIALIZER_MAP.get(deserializationContext.getContextualType().getRawClass());
                if (deserializer == null) {
                    deserializer = new EnumDeserializer();
                    deserializer.setTypeClass((Class<? extends Enum>) deserializationContext.getContextualType().getRawClass());
                    DESERIALIZER_MAP.put(deserializationContext.getContextualType().getRawClass(), deserializer);
                }
            }
        }
        return deserializer;
    }

    public void setTypeClass(Class<? extends Enum> typeClass) {
        this.typeClass = typeClass;
    }
}
