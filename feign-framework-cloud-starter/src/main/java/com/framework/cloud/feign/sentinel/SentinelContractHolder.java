package com.framework.cloud.feign.sentinel;

import feign.Contract;
import feign.MethodMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重写Sentinel降级处理器
 *
 * @author wusiwei
 */
public class SentinelContractHolder implements Contract {

    private final Contract delegate;
    public static final Map<String, MethodMetadata> METADATA_MAP = new HashMap<>();

    public SentinelContractHolder(Contract delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<MethodMetadata> parseAndValidateMetadata(Class<?> targetType) {
        List<MethodMetadata> metaData = this.delegate.parseAndValidateMetadata(targetType);
        metaData.forEach((metadata) -> {
            METADATA_MAP.put(targetType.getName() + metadata.configKey(), metadata);
        });
        return metaData;
    }

}
