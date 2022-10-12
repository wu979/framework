package com.framework.cloud.event.listener;

import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.event.application.ApplicationLocalEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * 本地事件监听
 *
 * @author wusiwei
 */
@Slf4j
public class ApplicationLocalListener implements ApplicationListener<ApplicationLocalEvent> {

    @Override
    public void onApplicationEvent(ApplicationLocalEvent event) {
        log.info("收到本地事件消息：{}", FastJsonUtil.toJSONString(event));
    }
}
