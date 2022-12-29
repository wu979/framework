package com.framework.cloud.mongodb.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * Mongo事务
 *
 * @author wusiwei
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "MONGO_TRANSACTION_MANAGER")
public @interface MongoTransactional {

    @AliasFor(
            annotation = Transactional.class,
            attribute = "propagation"
    )
    Propagation propagation() default Propagation.REQUIRED;

    @AliasFor(
            annotation = Transactional.class,
            attribute = "isolation"
    )
    Isolation isolation() default Isolation.DEFAULT;

    @AliasFor(
            annotation = Transactional.class,
            attribute = "rollbackFor"
    )
    Class<? extends Throwable>[] rollbackFor() default {};

    @AliasFor(
            annotation = Transactional.class,
            attribute = "noRollbackFor"
    )
    Class<? extends Throwable>[] noRollbackFor() default {};

    @AliasFor(
            annotation = Transactional.class,
            attribute = "readOnly"
    )
    boolean readOnly() default false;
}
