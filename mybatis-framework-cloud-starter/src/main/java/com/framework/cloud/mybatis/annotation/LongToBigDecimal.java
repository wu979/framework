package com.framework.cloud.mybatis.annotation;

import com.baomidou.mybatisplus.annotation.TableField;
import com.framework.cloud.mybatis.hander.MybatisBigDecimalTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Long转BigDecimal 字段注解
 *
 * @author wusiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@TableField(jdbcType = JdbcType.BIGINT, typeHandler = MybatisBigDecimalTypeHandler.class)
public @interface LongToBigDecimal {
}
