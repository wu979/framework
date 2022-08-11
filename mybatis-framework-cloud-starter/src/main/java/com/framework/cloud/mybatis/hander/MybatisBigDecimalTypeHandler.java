package com.framework.cloud.mybatis.hander;

import com.framework.cloud.common.utils.AmountUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Long转BigDecimal处理器
 *
 * @author wusiwei
 */
@MappedTypes(BigDecimal.class)
@MappedJdbcTypes(value = JdbcType.BIGINT, includeNullJdbcType = true)
public class MybatisBigDecimalTypeHandler extends BaseTypeHandler<BigDecimal> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BigDecimal parameter, JdbcType jdbcType) throws SQLException {
        ParameterMetaData parameterMetaData = ps.getParameterMetaData();
        ps.setLong(i , AmountUtil.decimalToLong(parameter));
    }

    @Override
    public BigDecimal getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return AmountUtil.longToDecimal(rs.getLong(columnName));
    }

    @Override
    public BigDecimal getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return AmountUtil.longToDecimal(rs.getLong(columnIndex));
    }

    @Override
    public BigDecimal getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return AmountUtil.longToDecimal(cs.getLong(columnIndex));
    }
}
