package com.framework.cloud.executors.decorator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 *
 * @author wusiwei
 */
@Slf4j
@AllArgsConstructor
@SuppressWarnings({"rawtypes"})
public class ContextDecorator implements TaskDecorator {

    private final ServletRequestListener servletRequestListener;

    @Override
    public Runnable decorate(Runnable runnable) {
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
                if (runnable instanceof Future) {
                    ((Future) runnable).get();
                }
            } catch (InterruptedException | ExecutionException e) {
                log.error("future task errpr: {}", e);
                throw new IllegalThreadStateException(e.getMessage());
            } catch (Exception e) {
                log.error("errpr: {}", e);
                throw e;
            }
        };
    }
}
