package com.framework.cloud.core.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;

/**
 * 初始化后置事件 防止多次执行
 *
 * @author wusiwei
 */
public class ApplicationPostProcessor implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private ApplicationContext applicationContext;

    private boolean executeOnlyOnce = true;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        synchronized (ApplicationPostProcessor.class) {
            if (executeOnlyOnce) {
                applicationContext.publishEvent(new ApplicationInitializingEvent(this));
                executeOnlyOnce = false;
            }
        }
    }
}
