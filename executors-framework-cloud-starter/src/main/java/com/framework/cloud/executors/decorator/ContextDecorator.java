package com.framework.cloud.executors.decorator;

import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.utils.TraceUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.RunnableWrapper;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author wusiwei
 */
@Slf4j
@AllArgsConstructor
@SuppressWarnings({"all"})
public class ContextDecorator implements TaskDecorator {

    private final ServletRequestListener servletRequestListener;


    @Override
    public Runnable decorate(Runnable runnable) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Optional<ServletRequestEvent> serlvet = requestEvent(attributes);
        String traceId = TraceUtil.traceId();
        return () -> {
            Optional<ServletRequestEvent> childThreadSerlvet = serlvet;
            try {
                childThreadSerlvet.ifPresent(servletRequestListener::requestInitialized);
                MDC.put(HeaderConstant.TRACE_ID, traceId);
                RunnableWrapper.of(runnable).run();
            } finally {
                childThreadSerlvet.ifPresent(servletRequestListener::requestDestroyed);
                MDC.remove(HeaderConstant.TRACE_ID);
            }
        };
    }

    private Optional<ServletRequestEvent> requestEvent(ServletRequestAttributes attributes) {
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return Optional.of(new ServletRequestEvent(request.getServletContext(), request));
        }
        return Optional.empty();
    }
}
