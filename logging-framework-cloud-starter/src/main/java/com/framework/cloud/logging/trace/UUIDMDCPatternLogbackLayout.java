package com.framework.cloud.logging.trace;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.framework.cloud.common.utils.UUIDUtil;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.LogbackPatternConverter;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.TraceIdPatternLogbackLayout;

/**
 *
 *
 * @author wusiwei
 */
public class UUIDMDCPatternLogbackLayout extends TraceIdPatternLogbackLayout {

    static {
        defaultConverterMap.put("tid", UUIDConverter.class.getName());
    }

    public static class UUIDConverter extends LogbackPatternConverter {

        private static final UUIDConverterCommand uuidConverterCommand = new UUIDCommand();

        @Override
        public String convert(ILoggingEvent iLoggingEvent) {
            return uuidConverterCommand.convert(iLoggingEvent);
        }

    }

    private interface UUIDConverterCommand {

        String convert(ILoggingEvent loggingEvent);
    }

    private static class UUIDCommand implements UUIDConverterCommand {

        @Override
        public String convert(ILoggingEvent loggingEvent) {
            return UUIDUtil.uuid();
        }
    }

}
