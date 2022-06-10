package com.framework.cloud.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 顶级持久层
 *
 * @author wusiwei
 */
public interface BaseRepository<T> extends IService<T> {

    /**
     * 根据主键查询详情 未找到数据异常404
     *
     * @param id 主键
     * @return 泛型实体
     */
    T getByIdNotNull(Serializable id);

}
