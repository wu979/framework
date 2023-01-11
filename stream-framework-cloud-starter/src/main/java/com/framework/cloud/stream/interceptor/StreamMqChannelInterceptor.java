package com.framework.cloud.stream.interceptor;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.common.utils.StringUtil;
import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.TokenContextHolder;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.model.LoginTenant;
import com.framework.cloud.holder.model.LoginUser;
import com.framework.cloud.holder.utils.TraceUtil;
import com.google.common.collect.Sets;
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
public class StreamMqChannelInterceptor implements ChannelInterceptor {

    private final Field headerField;

    @Resource
    private ObjectMapper objectMapper;

    public StreamMqChannelInterceptor() {
        this.headerField = ReflectionUtils.findField(MessageHeaders.class, "headers");
        this.headerField.setAccessible(true);
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message instanceof ErrorMessage ? message : this.doPreSend(message, channel);
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        return message instanceof ErrorMessage ? message : this.doPostReceive(message, channel);
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

    private Message<?> doPostReceive(Message<?> message, MessageChannel channel) {
        Map<String, Object> headersMap = (Map<String, Object>) ReflectionUtils.getField(this.headerField, message.getHeaders());
        Optional<String> token = converterContent(headersMap.get(HeaderConstant.AUTHORIZATION), String::valueOf);
        if (token.isPresent()) {
            TokenContextHolder.getInstance().setToken(token.get());
        }
        Optional<String> traceId = converterContent(headersMap.get(HeaderConstant.TRACE_ID), String::valueOf);
        if (traceId.isPresent()) {
            MDC.put(HeaderConstant.TRACE_ID, traceId.get());
        }
        Optional<String> user = converterContent(headersMap.get(HeaderConstant.X_USER_HEADER), Base64::decodeStr);
        if (user.isPresent()) {
            UserContextHolder.getInstance().setUser(FastJsonUtil.toJavaObject(user.get(), LoginUser.class));
        }
        Optional<String> tenant = converterContent(headersMap.get(HeaderConstant.X_TENANT_HEADER), Base64::decodeStr);
        if (tenant.isPresent()) {
            TenantContextHolder.getInstance().setTenant(FastJsonUtil.toJavaObject(tenant.get(), LoginTenant.class));
        }
        Optional<String> role = converterContent(headersMap.get(HeaderConstant.X_AUTHORITIES_HEADER), Base64::decodeStr);
        if (role.isPresent()) {
            UserRoleContextHolder.getInstance().setRoleList(Sets.newHashSet(StrUtil.splitTrim(role.get(), ",")));
        }
        return message;
    }

    private <T> Optional<String> getContent(Supplier<T> supplier) {
        return Optional.ofNullable(supplier.get()).filter(Objects::nonNull).map(String::valueOf).filter(StringUtil::isNotEmpty);
    }

    private <T> Optional<String> getContent(T data, Function<T, String> function) {
        return Optional.ofNullable(function.apply(data)).filter(Objects::nonNull).map(String::valueOf).filter(StringUtil::isNotEmpty);
    }

    private <R> Optional<R> converterContent(Object obj, Function<String, R> function) {
        return Optional.ofNullable(obj).filter(Objects::nonNull).map(String::valueOf).filter(StringUtil::isNotEmpty).map(function::apply);
    }

}
