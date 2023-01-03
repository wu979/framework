package com.framework.cloud.holder.utils;

import com.framework.cloud.holder.constant.HeaderConstant;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;

/**
 * 链路信息
 *
 * @author wusiwei
 */
public class TraceUtil {

    /**
     * 系统链路（UUID）
     */
    public static String traceId() {
        return MDC.get(HeaderConstant.TRACE_ID);
    }

    /**
     * 系统链路（Skywalking trace）
     */
    public static String tid() {
        return TraceContext.traceId();
    }
}
