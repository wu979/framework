package com.framework.cloud.enums.platform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.framework.cloud.common.base.BaseEnum;
import com.framework.cloud.swagger.annotation.SwaggerDisplayEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付方式
 *
 * @author wusiwei
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@SwaggerDisplayEnum
public enum PayModeType implements BaseEnum<String> {

    /** code 第三方枚举 value 数据库 label 汉译 */
    WX_JSAPI(11, "微信公众号支付"),
    WX_NATIVE(12, "微信扫码支付"),
    WX_APP(10, "微信唤醒支付"),
    WX_MWEB(13, "微信H5支付"),
    WX_MICROPAY(14, "微信付款码支付"),
    WX_FACEPAY(15, "微信刷脸支付"),

    ZFB_APP(20, "支付宝唤醒支付"),
    ZFB_MOBILE(21, "支付宝手机网站支付"),
    ZFB_COMPUTER(22, "支付宝电脑网站支付"),
    ZFB_FACE(23, "支付宝刷脸支付"),
    ZFB_NATIVE(24, "支付宝扫码支付"),
    ZFB_PAYMENT(25, "支付宝付款码支付"),

    YL_CARD(30, "银联银行卡支付")
    ;

    private final int code;
    private final String label;

    @Override
    public String getValue() {
        return this.name();
    }
}
