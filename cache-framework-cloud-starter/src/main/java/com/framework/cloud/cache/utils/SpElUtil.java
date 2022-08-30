package com.framework.cloud.cache.utils;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Objects;

/**
 * @author wusiwei
 */
public class SpElUtil {

    private static final SpelExpressionParser PARSER;

    private static final DefaultParameterNameDiscoverer DISCOVERER;

    private SpElUtil() {
    }

    static {
        PARSER = new SpelExpressionParser();
        DISCOVERER = new DefaultParameterNameDiscoverer();
    }

    /**
     * 解析 spEL
     */
    public static String analysisValBySpEl(String lockKeyEl, MethodSignature methodSignature, Object[] args) {
        // 获取方法形参名数组
        String[] paramNames = DISCOVERER.getParameterNames(methodSignature.getMethod());
        if (paramNames != null && paramNames.length > 0) {
            Expression expression = PARSER.parseExpression(lockKeyEl);
            // spring 表达式上下文对象
            EvaluationContext context = new StandardEvaluationContext();
            // 给上下文赋值
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            return Objects.requireNonNull(expression.getValue(context)).toString();
        }
        return null;
    }
}
