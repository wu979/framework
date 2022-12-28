package com.framework.cloud.executors.decorator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

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
        try {
            ServletRequestEvent requestEvent = null;
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                requestEvent = new ServletRequestEvent(request.getServletContext(), request);
            }
            final ServletRequestEvent finalRequestEvent = requestEvent;
            return () -> {
                if (finalRequestEvent != null) {
                    servletRequestListener.requestInitialized(finalRequestEvent);
                }
                try {
                    runnable.run();
                } finally {
                    servletRequestListener.requestDestroyed(finalRequestEvent);
                }
            };
        } catch (IllegalStateException e) {
            return runnable;
        }
    }
}
