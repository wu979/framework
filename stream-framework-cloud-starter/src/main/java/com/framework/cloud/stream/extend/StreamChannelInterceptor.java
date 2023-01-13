package com.framework.cloud.stream.extend;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.common.utils.StringUtil;
import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.TokenContextHolder;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.utils.TraceUtil;
import org.slf4j.MDC;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * stream rabbit request header
 *
 * @author wusiwei
 */
@SuppressWarnings("all")
public class StreamChannelInterceptor implements ChannelInterceptor {

    private final Field headerField;

    @Resource
    private ObjectMapper objectMapper;

    public StreamChannelInterceptor() {
        this.headerField = ReflectionUtils.findField(MessageHeaders.class, "headers");
        this.headerField.setAccessible(true);
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message instanceof ErrorMessage ? message : this.doPreSend(message, channel);
    }

    private Message<?> doPreSend(Message<?> message, MessageChannel channel) {
        Map<String, Object> headersMap = (Map<String, Object>) ReflectionUtils.getField(this.headerField, message.getHeaders());
        Optional<String> token, traceId, user, tenant, role;
        Object tokenHeader = headersMap.get(HeaderConstant.AUTHORIZATION);
        if (Objects.nonNull(tokenHeader) && StringUtil.isNotEmpty(tokenHeader.toString())) {
            token = Optional.of(tokenHeader.toString());
        } else {
            token = getContent(TokenContextHolder.getInstance()::getToken);
        }
        Object traceIdHeader = headersMap.get(HeaderConstant.TRACE_ID);
        if (Objects.nonNull(traceIdHeader) && StringUtil.isNotEmpty(traceIdHeader.toString())) {
            traceId = Optional.of(traceIdHeader.toString());
        } else {
            traceId = getContent(TraceUtil::traceId);
        }
        Object userHeader = headersMap.get(HeaderConstant.X_USER_HEADER);
        if (Objects.nonNull(userHeader) && StringUtil.isNotEmpty(userHeader.toString())) {
            user = Optional.of(userHeader.toString());
        } else {
            user= getContent(FastJsonUtil.toJSONString(UserContextHolder.getInstance().getUser()), Base64::encode);
        }
        Object tenantHeader = headersMap.get(HeaderConstant.X_TENANT_HEADER);
        if (Objects.nonNull(tenantHeader) && StringUtil.isNotEmpty(tenantHeader.toString())) {
            tenant = Optional.of(tenantHeader.toString());
        } else {
            tenant = getContent(FastJsonUtil.toJSONString(TenantContextHolder.getInstance().getTenant()), Base64::encode);
        }
        Object roleHeader = headersMap.get(HeaderConstant.X_AUTHORITIES_HEADER);
        if (Objects.nonNull(roleHeader) && StringUtil.isNotEmpty(roleHeader.toString())) {
            role = Optional.of(roleHeader.toString());
        } else {
            role = getContent(CollectionUtil.join(UserRoleContextHolder.getInstance().getRoleList(), ","), Base64::encode);
        }
        traceId.ifPresent(content -> MDC.put(HeaderConstant.TRACE_ID, traceId.get()));
        traceId.ifPresent(content -> headersMap.put(HeaderConstant.TRACE_ID, traceId.get()));
        token.ifPresent(content -> headersMap.put(HeaderConstant.AUTHORIZATION, content));
        user.ifPresent(content -> headersMap.put(HeaderConstant.X_USER_HEADER, content));
        tenant.ifPresent(content -> headersMap.put(HeaderConstant.X_TENANT_HEADER, content));
        role.ifPresent(content -> headersMap.put(HeaderConstant.X_AUTHORITIES_HEADER, content));
        return message;
    }

    private <T> Optional<String> getContent(Supplier<T> supplier) {
        return Optional.ofNullable(supplier.get()).filter(Objects::nonNull).map(String::valueOf).filter(StringUtil::isNotEmpty);
    }

    private <T> Optional<String> getContent(T data, Function<T, String> function) {
        return Optional.ofNullable(function.apply(data)).filter(Objects::nonNull).map(String::valueOf).filter(StringUtil::isNotEmpty);
    }

}
