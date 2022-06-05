package com.framework.cloud.mybatis.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 顶级实现层
 *
 * @author wusiwei
 */
public class BaseServiceImpl <M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
}
