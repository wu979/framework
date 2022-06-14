package com.framework.cloud.feign.fallback;

import feign.Target;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * 全局Feign工厂
 *
 * @author wusiwei
 */
@AllArgsConstructor
public class OverallFallbackFactory<T> implements FallbackFactory<T> {

    private final Target<T> target;

    @Override
    @SuppressWarnings("unchecked")
    public T create(Throwable throwable) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new OverallFeignFallback<>(targetType, targetName, throwable));
        return (T) enhancer.create();
    }

}
