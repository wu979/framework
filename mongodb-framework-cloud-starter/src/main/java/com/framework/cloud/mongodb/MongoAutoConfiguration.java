package com.framework.cloud.mongodb;

import com.framework.cloud.mongodb.annotation.MongoTransactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * mongodb auto configuration
 *
 * @author wusiwei
 */
public class MongoAutoConfiguration {

    /**
     * global mongodb transaction, do not use global transactions, can be used instead {@link MongoTransactional }
     */
    @Bean
    @ConditionalOnProperty(prefix = "spring.data.mongodb", value = "transaction", havingValue = "true", matchIfMissing = true)
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}
