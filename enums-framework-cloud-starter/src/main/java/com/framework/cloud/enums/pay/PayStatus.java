package com.framework.cloud.enums.pay;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.framework.cloud.common.base.BaseEnum;
import com.framework.cloud.swagger.annotation.SwaggerDisplayEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态
 *
 * @author wusiwei
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@SwaggerDisplayEnum
public enum PayStatus implements BaseEnum<String> {

    /** code 第三方枚举 value 数据库 label 汉译 */
    WAIT_PAY(0, "待支付"),
    IN_PAY(1, "支付中"),
    ALREADY_PAY(2, "已支付"),
    IN_REFUND(3, "退款中"),
    ALREADY_REFUND(4, "已退款"),
    REFUSE_REFUND(5, "拒绝退款"),
    CANCEL_REFUND(6, "取消退款"),
    CLOSED(7, "交易关闭")
    ;
    private final int code;
    private final String label;

    @Override
    public String getValue() {
        return this.name();
    }
}
