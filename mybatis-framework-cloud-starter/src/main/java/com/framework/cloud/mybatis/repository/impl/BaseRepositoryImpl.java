package com.framework.cloud.mybatis.repository.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 顶级数据实现层
 *
 * @author wusiwei
 */
public class BaseRepositoryImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
}
