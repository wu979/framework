package com.framework.cloud.common.base;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;

/**
 * @author wusiwei
 */
public interface BaseEnum<T extends Serializable> extends IEnum<T> {

    /**
     * 第三方枚举值
     */
    int getCode();

    /**
     * 汉译
     */
    String getLabel();

    /**
     * 数据库存值
     */
    @Override
    T getValue();

}
