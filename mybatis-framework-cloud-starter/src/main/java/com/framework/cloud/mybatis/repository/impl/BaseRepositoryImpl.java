package com.framework.cloud.mybatis.repository.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.cloud.mybatis.enums.MybatisMessageEnum;
import com.framework.cloud.mybatis.exception.NotFoundException;

import java.io.Serializable;
import java.util.Objects;

/**
 * 顶级持久层
 *
 * @author wusiwei
 */
public class BaseRepositoryImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    public T getByIdNotNull(Serializable id) {
        T info = super.getById(id);
        if (Objects.isNull(info)) {
            throw new NotFoundException(MybatisMessageEnum.NOT_FOUND.getCode(), MybatisMessageEnum.NOT_FOUND.getMsg());
        }
        return info;
    }
}
