package com.framework.cloud.stream.rabbit.listener;

import com.framework.cloud.stream.message.StreamMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 *
 * @author wusiwei
 */
@Data
@AllArgsConstructor
public class TestMessage implements StreamMessage {
    private static final long serialVersionUID = -8093478592086631297L;

    private String name;

    @Override
    public Boolean isDelay() {
        return true;
    }

    @Override
    public long expiration() {
        return 10L;
    }
}
