package com.framework.cloud.feign.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.framework.cloud.feign.constant.FeignConstant;
import feign.*;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 重写Sentinel降级处理器
 *
 * @author wusiwei
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class SentinelInvocationHandler implements InvocationHandler {

    private final Target<?> target;
    private final Map<Method, InvocationHandlerFactory.MethodHandler> dispatch;
    private FallbackFactory fallbackFactory;
    private Map<Method, Method> fallbackMethodMap;

    SentinelInvocationHandler(Target<?> target, Map<Method, InvocationHandlerFactory.MethodHandler> dispatch, FallbackFactory fallbackFactory) {
        this.target = (Target) Util.checkNotNull(target, FeignConstant.TARGET, new Object[0]);
        this.dispatch = (Map) Util.checkNotNull(dispatch, FeignConstant.DISPATCH, new Object[0]);
        this.fallbackFactory = fallbackFactory;
        this.fallbackMethodMap = toFallbackMethod(dispatch);
    }

    SentinelInvocationHandler(Target<?> target, Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
        this.target = (Target) Util.checkNotNull(target, FeignConstant.TARGET, new Object[0]);
        this.dispatch = (Map) Util.checkNotNull(dispatch, FeignConstant.DISPATCH, new Object[0]);
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        switch (method.getName()) {
            case FeignConstant.EQUALS:
                try {
                    Object otherHandler = args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                    return this.equals(otherHandler);
                } catch (IllegalArgumentException var21) {
                    return false;
                }
            case FeignConstant.HASH_CODE:
                return this.hashCode();
            case FeignConstant.TO_STRING:
                return this.toString();
            default:
                InvocationHandlerFactory.MethodHandler methodHandler = (InvocationHandlerFactory.MethodHandler) this.dispatch.get(method);
                Object result;
                if (!(this.target instanceof Target.HardCodedTarget)) {
                    result = methodHandler.invoke(args);
                } else {
                    Target.HardCodedTarget hardCodedTarget = (Target.HardCodedTarget) this.target;
                    MethodMetadata methodMetadata = (MethodMetadata) SentinelContractHolder.METADATA_MAP.get(hardCodedTarget.type().getName() + Feign.configKey(hardCodedTarget.type(), method));
                    if (methodMetadata == null) {
                        result = methodHandler.invoke(args);
                    } else {
                        String resourceName = methodMetadata.template().method().toUpperCase() + ":" + hardCodedTarget.url() + methodMetadata.template().path();
                        Entry entry = null;

                        Object var12;
                        try {
                            Throwable ex;
                            try {
                                ContextUtil.enter(resourceName);
                                entry = SphU.entry(resourceName, EntryType.OUT, 1, args);
                                result = methodHandler.invoke(args);
                                return result;
                            } catch (Throwable var22) {
                                ex = var22;
                                if (!BlockException.isBlockException(var22)) {
                                    Tracer.trace(var22);
                                }
                            }
                            if (this.fallbackFactory == null) {
                                throw ex;
                            }
                            try {
                                var12 = ((Method) this.fallbackMethodMap.get(method)).invoke(this.fallbackFactory.create(ex), args);
                            } catch (IllegalAccessException var19) {
                                throw new AssertionError(var19);
                            } catch (InvocationTargetException var20) {
                                throw new AssertionError(var20.getCause());
                            }
                        } finally {
                            if (entry != null) {
                                entry.exit(1, args);
                            }

                            ContextUtil.exit();
                        }

                        return var12;
                    }
                }

                return result;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SentinelInvocationHandler) {
            SentinelInvocationHandler other = (SentinelInvocationHandler) obj;
            return this.target.equals(other.target);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.target.hashCode();
    }

    @Override
    public String toString() {
        return this.target.toString();
    }

    static Map<Method, Method> toFallbackMethod(Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
        Map<Method, Method> result = new LinkedHashMap();
        for (Method method : dispatch.keySet()) {
            method.setAccessible(true);
            result.put(method, method);
        }
        return result;
    }

}
