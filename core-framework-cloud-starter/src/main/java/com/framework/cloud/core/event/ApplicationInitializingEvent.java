package com.framework.cloud.core.event;

import org.springframework.context.ApplicationEvent;

/**
 * 初始化事件
 *
 * @author wusiwei
 */
public class ApplicationInitializingEvent extends ApplicationEvent {
    private static final long serialVersionUID = 8164582472320667141L;

    public ApplicationInitializingEvent(Object source) {
        super(source);
    }

}
