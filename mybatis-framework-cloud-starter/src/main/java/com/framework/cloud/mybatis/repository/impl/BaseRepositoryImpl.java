package com.framework.cloud.mybatis.repository.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.cloud.mybatis.enums.MybatisMessage;
import com.framework.cloud.common.exception.NotFoundException;

import java.io.Serializable;
import java.util.Objects;

/**
 * 顶级持久层
 *
 * @author wusiwei
 */
public class BaseRepositoryImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    public T getByIdNotNull(Serializable id) {
        return getByIdNotNull(id, MybatisMessage.NOT_FOUND.getMsg());
    }

    public T getByIdNotNull(Serializable id, String errorMsg) {
        T info = super.getById(id);
        if (Objects.isNull(info)) {
            throw new NotFoundException(MybatisMessage.NOT_FOUND.getCode(), errorMsg);
        }
        return info;
    }

}
