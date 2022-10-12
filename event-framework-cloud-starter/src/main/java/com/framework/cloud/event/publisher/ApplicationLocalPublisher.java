package com.framework.cloud.event.publisher;

import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.event.application.ApplicationLocalEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 本地事件发布
 *
 * @author wusiwei
 */
@Slf4j
public class ApplicationLocalPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(ApplicationLocalEvent event) {
        log.info("发布本地事件消息：{}", FastJsonUtil.toJSONString(event));
        applicationEventPublisher.publishEvent(event);
    }

}
