package com.framework.cloud.stream.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.common.utils.StringUtil;
import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.TokenContextHolder;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.model.LoginTenant;
import com.framework.cloud.holder.model.LoginUser;
import com.framework.cloud.holder.utils.OauthUtil;
import com.framework.cloud.holder.utils.TraceUtil;
import com.google.common.collect.Sets;
import lombok.experimental.UtilityClass;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 *
 * @author wusiwei
 */
@UtilityClass
@SuppressWarnings("all")
public class MessageHeaderUtil {

    /**
     * 发送消息前 配置请求头
     * @param headers 请求头
     * @param consumer 消费操作 Map::put
     */
    public void doPreHeaders(Map<String, Object> headers, BiConsumer<String, String> consumer) {
        //令牌
        Object tokenHeader = headers.get(HeaderConstant.AUTHORIZATION);
        if (Objects.isNull(tokenHeader) || StringUtil.isEmpty(tokenHeader.toString())) {
            Optional<String> token = MessageHeaderUtil.getContent(TokenContextHolder.getInstance()::getToken);
            token.ifPresent(content -> consumer.accept(HeaderConstant.AUTHORIZATION, content));
        }
        //链路
        Object traceIdHeader = headers.get(HeaderConstant.TRACE_ID);
        if (Objects.isNull(traceIdHeader) || StringUtil.isEmpty(traceIdHeader.toString())) {
            Optional<String> traceId = MessageHeaderUtil.getContent(TraceUtil::traceId);
            if (traceId.isPresent()) {
                MDC.put(HeaderConstant.TRACE_ID, traceId.get());
                consumer.accept(HeaderConstant.TRACE_ID, traceId.get());
            }
        }
        //用户
        Object userHeader = headers.get(HeaderConstant.X_USER_HEADER);
        if (Objects.isNull(userHeader) || StringUtil.isEmpty(userHeader.toString())) {
            Optional<String> user = MessageHeaderUtil.getContent(FastJsonUtil.toJSONString(OauthUtil.getUser()), Base64::encode);
            user.ifPresent(content -> consumer.accept(HeaderConstant.X_USER_HEADER, content));
        }
        //租户
        Object tenantHeader = headers.get(HeaderConstant.X_TENANT_HEADER);
        if (Objects.isNull(tenantHeader) || StringUtil.isEmpty(tenantHeader.toString())) {
            Optional<String> tenant = MessageHeaderUtil.getContent(FastJsonUtil.toJSONString(OauthUtil.getTenant()), Base64::encode);
            tenant.ifPresent(content -> consumer.accept(HeaderConstant.X_TENANT_HEADER, content));
        }
        //角色
        Object roleHeader = headers.get(HeaderConstant.X_AUTHORITIES_HEADER);
        if (Objects.isNull(roleHeader) || StringUtil.isEmpty(roleHeader.toString())) {
            Optional<String> role = MessageHeaderUtil.getContent(CollectionUtil.join(OauthUtil.getRoleList(), ","), Base64::encode);
            role.ifPresent(content -> consumer.accept(HeaderConstant.X_AUTHORITIES_HEADER, content));
        }
    }

    /**
     * 收到消息后 配置上下文
     * @param headers
     */
    public void doPostHeaders(Map<String, Object> headers) {
        Optional<String> token = converterContent(headers.get(HeaderConstant.AUTHORIZATION), String::valueOf);
        if (token.isPresent()) {
            TokenContextHolder.getInstance().setToken(token.get());
        }
        Optional<String> traceId = converterContent(headers.get(HeaderConstant.TRACE_ID), String::valueOf);
        if (traceId.isPresent()) {
            MDC.put(HeaderConstant.TRACE_ID, traceId.get());
        }
        Optional<String> user = converterContent(headers.get(HeaderConstant.X_USER_HEADER), Base64::decodeStr);
        if (user.isPresent()) {
            UserContextHolder.getInstance().setUser(FastJsonUtil.toJavaObject(user.get(), LoginUser.class));
        }
        Optional<String> tenant = converterContent(headers.get(HeaderConstant.X_TENANT_HEADER), Base64::decodeStr);
        if (tenant.isPresent()) {
            TenantContextHolder.getInstance().setTenant(FastJsonUtil.toJavaObject(tenant.get(), LoginTenant.class));
        }
        Optional<String> role = converterContent(headers.get(HeaderConstant.X_AUTHORITIES_HEADER), Base64::decodeStr);
        if (role.isPresent()) {
            UserRoleContextHolder.getInstance().setRoleList(Sets.newHashSet(StrUtil.splitTrim(role.get(), ",")));
        }
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
