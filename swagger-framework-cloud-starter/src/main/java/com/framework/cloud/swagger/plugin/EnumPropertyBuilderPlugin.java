package com.framework.cloud.swagger.plugin;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.framework.cloud.swagger.annotation.SwaggerDisplayEnum;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.util.ReflectionUtils;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.builders.PropertySpecificationBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Swagger show enumeration value plug-in
 *
 * @author wusiwei
 */
@Order(SwaggerPluginSupport.OAS_PLUGIN_ORDER)
public class EnumPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public void apply(ModelPropertyContext context) {
        if (context == null) {
            return;
        }
        Optional<BeanPropertyDefinition> optional = context.getBeanPropertyDefinition();
        if (!optional.isPresent()) {
            return;
        }
        BeanPropertyDefinition beanPropertyDefinition = optional.get();
        AnnotatedField field = beanPropertyDefinition.getField();
        if (null != field) {
            addDescForEnum(context, field.getRawType());
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    private void addDescForEnum(ModelPropertyContext context, Class<?> fieldType) {
        if (Enum.class.isAssignableFrom(fieldType)) {
            SwaggerDisplayEnum annotation = AnnotationUtils.findAnnotation(fieldType, SwaggerDisplayEnum.class);
            if (annotation != null) {
                String name = annotation.name();
                String label = annotation.label();
                Object[] enumConstants = fieldType.getEnumConstants();
                List<String> displayValues =
                        Arrays.stream(enumConstants)
                                .filter(Objects::nonNull)
                                .map(item -> {
                                    Class<?> currentClass = item.getClass();
                                    Field labelField = ReflectionUtils.findField(currentClass, label);
                                    ReflectionUtils.makeAccessible(labelField);
                                    Object desc = ReflectionUtils.getField(labelField, item);
                                    Field descField = ReflectionUtils.findField(currentClass, name);
                                    ReflectionUtils.makeAccessible(descField);
                                    Object value = ReflectionUtils.getField(descField, item);
                                    return value + "：" + desc;
                                }).collect(Collectors.toList());
                ModelPropertyBuilder builder = context.getBuilder();
                PropertySpecificationBuilder specificationBuilder = context.getSpecificationBuilder();
                Field descField = ReflectionUtils.findField(specificationBuilder.getClass(), "description");
                ReflectionUtils.makeAccessible(descField);
                String joinText = ReflectionUtils.getField(descField, specificationBuilder) + " （ " + String.join("  、 ", displayValues) + " ）";
                context.getSpecificationBuilder().description(joinText);
            }
        }

    }

}
