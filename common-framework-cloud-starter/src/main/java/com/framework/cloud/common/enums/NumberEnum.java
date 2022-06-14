package com.framework.cloud.common.enums;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

/**
 * 数字枚举
 *
 * @author wusiwei
 */
@AllArgsConstructor
public enum NumberEnum {

    /**
     * 数字类枚举
     */
    ZERO(0, 0L, "0", 0.00, BigDecimal.valueOf(0)),
    ONE(1, 1L, "1", 1.00, BigDecimal.valueOf(1)),
    TWO(2, 2L, "2", 2.00, BigDecimal.valueOf(2)),
    THREE(3, 3L, "3", 3.00, BigDecimal.valueOf(3)),
    FOUR(4, 4L, "4", 4.00, BigDecimal.valueOf(4)),
    FIVE(5, 5L, "5", 5.00, BigDecimal.valueOf(5)),
    SIX(6, 6L, "6", 6.00, BigDecimal.valueOf(6)),
    SEVEN(7, 7L, "7", 7.00, BigDecimal.valueOf(7)),
    EIGHT(8, 8L, "8", 8.00, BigDecimal.valueOf(8)),
    NINE(9, 9L, "9", 9.00, BigDecimal.valueOf(9)),
    ;

    Integer intValue;
    Long longValue;
    String strValue;
    Double doubleValue;
    BigDecimal decimalValue;

    public Integer getIntValue() {
        return intValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public BigDecimal getDecimalValue() {
        return decimalValue;
    }
}
