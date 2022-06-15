package com.framework.cloud.mybatis.properties;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * mybatis 配置文件
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "framework")
public class MybatisProperties {

    private MybatisPlusProperties mybatisPlus;
}
