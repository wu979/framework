package com.framework.cloud.mybatis.primary;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.framework.cloud.mybatis.utils.IdUtil;
import org.springframework.stereotype.Component;

/**
 * 自定义雪花
 *
 * @author wusiwei
 */
@Component
public class SnowflakeIdGenerator implements IdentifierGenerator {

    @Override
    public Long nextId(Object entity) {
        return IdUtil.getId();
    }
}
