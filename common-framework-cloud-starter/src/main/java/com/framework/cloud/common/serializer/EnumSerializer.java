package com.framework.cloud.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.framework.cloud.common.base.BaseEnum;

import java.io.IOException;

/**
 * @author wusiwei
 */
@SuppressWarnings({"rawtypes"})
public class EnumSerializer extends JsonSerializer<Enum> {
    @Override
    public void serialize(Enum anEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (anEnum instanceof BaseEnum) {
            BaseEnum codeEnum = (BaseEnum) anEnum;
            jsonGenerator.writeObject(codeEnum.getValue());
        } else {
            jsonGenerator.writeString(anEnum.name());
        }
    }
}
