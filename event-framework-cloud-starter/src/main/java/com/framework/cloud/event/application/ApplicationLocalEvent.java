package com.framework.cloud.event.application;

import com.framework.cloud.common.utils.UUIDUtil;
import org.springframework.context.ApplicationEvent;

/**
 * 本地事件
 *
 * @author wusiwei
 */
public class ApplicationLocalEvent extends ApplicationEvent {
    private static final long serialVersionUID = 798219116259825838L;

    private String id;
    private String message;

    public ApplicationLocalEvent(Object source) {
        super(source);
        id = UUIDUtil.uuid();
    }

    public ApplicationLocalEvent(Object source, String message) {
        super(source);
        id = UUIDUtil.uuid();
        this.message = message;
    }

    public ApplicationLocalEvent(Object source, String id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
