package com.framework.cloud.mybatis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 雪花ID配置
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "mybatis-plus.snowflake")
public class SnowflakeProperties {

    private Integer timeBits;

    private Integer workerBits;

    private Integer seqBits;

    private Integer boostPower;

}
