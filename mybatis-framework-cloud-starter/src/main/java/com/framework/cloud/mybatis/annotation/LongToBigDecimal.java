package com.framework.cloud.mybatis.annotation;

import com.baomidou.mybatisplus.annotation.TableField;
import com.framework.cloud.mybatis.hander.MybatisBigDecimalTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Long转BigDecimal 字段注解 （无用）
 * <p>
 * TypeHandler 要么扫描要么注入 都是全局配置，使用TableField指定转换器无效，因为一旦注入，会默认使用 {@link MybatisBigDecimalTypeHandler }
 *
 * @author wusiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@TableField(jdbcType = JdbcType.BIGINT, typeHandler = MybatisBigDecimalTypeHandler.class)
public @interface LongToBigDecimal {
}
