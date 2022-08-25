package com.framework.cloud.enums.platform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.framework.cloud.common.base.BaseEnum;
import com.framework.cloud.swagger.annotation.SwaggerDisplayEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付渠道
 *
 * @author wusiwei
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@SwaggerDisplayEnum
public enum PayChannelType implements BaseEnum<String> {

    /** code 第三方枚举 value 数据库 label 汉译 */
    WX(0, "微信"),
    ZFB(1, "支付宝"),
    YL(2, "银联")
    ;

    private final int code;
    private final String label;

    @Override
    public String getValue() {
        return this.name();
    }
}
