package com.framework.cloud.stream;

import com.framework.cloud.stream.configuration.RabbitStreamConfiguration;
import org.springframework.context.annotation.Import;

/**
 * domain driven event initialization configuration
 *
 * @author wusiwei
 */
@Import(RabbitStreamConfiguration.class)
public class StreamAutoConfiguration {

}
