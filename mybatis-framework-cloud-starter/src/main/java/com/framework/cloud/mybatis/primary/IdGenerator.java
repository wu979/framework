package com.framework.cloud.mybatis.primary;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.framework.cloud.mybatis.utils.SnowflakeUtil;

/**
 * 自定义雪花
 *
 * @author wusiwei
 */
public class IdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return SnowflakeUtil.nextId();
    }
}
