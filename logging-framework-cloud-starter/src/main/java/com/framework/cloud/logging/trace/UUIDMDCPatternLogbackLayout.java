package com.framework.cloud.logging.trace;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.utils.TraceUtil;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.TraceIdPatternLogbackLayout;

/**
 * 重写TraceIdPatternLogbackLayout新增traceId写入日志
 *
 * @author wusiwei
 */
public class UUIDMDCPatternLogbackLayout extends TraceIdPatternLogbackLayout {

    static {
        defaultConverterMap.put(HeaderConstant.TRACE_ID, UUIDConverter.class.getName());
    }

    public static class UUIDConverter extends ClassicConverter {

        @Override
        public String convert(ILoggingEvent iLoggingEvent) {
            return TraceUtil.traceId();
        }
    }

}
