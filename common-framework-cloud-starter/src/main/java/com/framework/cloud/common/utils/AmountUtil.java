package com.framework.cloud.common.utils;

import com.framework.cloud.common.enums.GlobalNumber;

import java.math.BigDecimal;

/**
 * 全局金额工具
 *
 * @author wusiwei
 */
public final class AmountUtil {

    /**
     * 数据库存储的精度
     */
    private static final int DB_PRECISION = 8;
    /**
     * 幂运算，转正原始小数
     */
    private static final int DB_POWER = 10;
    /**
     * 精度
     */
    private static final int PRICE_PRECISION = 2;

    /**
     * Long To BigDecimal
     *
     * @param val 值
     * @return BigDecimal
     */
    public static BigDecimal longToDecimal(Long val) {
        if (null == val) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(val).divide(new BigDecimal(DB_POWER).pow(DB_PRECISION), PRICE_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * BigDecimal To Long
     *
     * @param val 值
     * @return Long
     */
    public static Long decimalToLong(BigDecimal val) {
        if (null == val) {
            return GlobalNumber.ZERO.getLongValue();
        }
        return val.setScale(DB_PRECISION, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(DB_POWER).pow(DB_PRECISION)).toBigIntegerExact().longValue();
    }
}
