package com.framework.cloud.cache.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wusiwei
 */
@Data
@AllArgsConstructor
public class ReadObject {

    private Object result;

    private Boolean isCache;

}
