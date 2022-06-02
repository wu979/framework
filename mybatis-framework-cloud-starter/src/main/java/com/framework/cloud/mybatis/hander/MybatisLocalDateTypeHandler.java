package com.framework.cloud.mybatis.hander;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LocalDateTypeHandler;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 *
 * @author wusiwei
 */
@MappedTypes(LocalDate.class)
@MappedJdbcTypes(value = JdbcType.DATE, includeNullJdbcType = true)
public class MybatisLocalDateTypeHandler extends LocalDateTypeHandler {

    @Override
    public LocalDate getResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        if (object instanceof java.sql.Date) {
            return ((Date) object).toLocalDate();
        }
        return null;
    }

}
