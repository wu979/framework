package com.framework.cloud.mybatis;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import com.framework.cloud.mybatis.properties.SnowflakeProperties;
import com.framework.cloud.mybatis.utils.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * baidu uid
 *
 * @author wusiwei
 */
@RequiredArgsConstructor
@EnableConfigurationProperties(SnowflakeProperties.class)
public class SnowflakeIdConfiguration {

    private final WorkerIdAssigner frameworkWorkerIdAssigner;
    private final SnowflakeProperties snowflakeProperties;

    @Bean
    @ConditionalOnMissingBean(CachedUidGenerator.class)
    public UidGenerator defaultUidGenerator() {
        return new AbstractIdChoose.DefaultIdChoose(frameworkWorkerIdAssigner, snowflakeProperties).create();
    }

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "mybatisPlus.snowflake", value = "type", havingValue = "cached")
    public UidGenerator cachedUidGenerator() {
        return new AbstractIdChoose.CachedIdChoose(frameworkWorkerIdAssigner, snowflakeProperties).create();
    }

    @SuppressWarnings("all")
    public abstract static class AbstractIdChoose {
        protected WorkerIdAssigner frameworkWorkerIdAssigner;
        protected static final String EPOCH = "2021-01-01";
        protected SnowflakeProperties snowflakeProperties;
        private final Field headerField;

        @SneakyThrows
        public UidGenerator create() {
            UidGenerator generator = uidGenerator();
            FieldUtils.writeStaticField(headerField, generator, true);
            return generator;
        }

        protected abstract UidGenerator uidGenerator() throws Exception;

        public AbstractIdChoose(WorkerIdAssigner frameworkWorkerIdAssigner, SnowflakeProperties snowflakeProperties) {
            this.frameworkWorkerIdAssigner = frameworkWorkerIdAssigner;
            this.snowflakeProperties = snowflakeProperties;
            this.headerField = ReflectionUtils.findField(IdUtil.class, "uidGenerator");
            this.headerField.setAccessible(true);
        }

        public static final class DefaultIdChoose extends AbstractIdChoose {

            public DefaultIdChoose(WorkerIdAssigner frameworkWorkerIdAssigner, SnowflakeProperties snowflakeProperties) {
                super(frameworkWorkerIdAssigner, snowflakeProperties);
            }

            @Override
            protected DefaultUidGenerator uidGenerator() {
                DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
                defaultUidGenerator.setWorkerIdAssigner(frameworkWorkerIdAssigner);
                defaultUidGenerator.setTimeBits(snowflakeProperties.getTimeBits());
                defaultUidGenerator.setWorkerBits(snowflakeProperties.getWorkerBits());
                defaultUidGenerator.setSeqBits(snowflakeProperties.getSeqBits());
                defaultUidGenerator.setEpochStr(EPOCH);
                return defaultUidGenerator;
            }

        }

        public static final class CachedIdChoose extends AbstractIdChoose {

            public CachedIdChoose(WorkerIdAssigner frameworkWorkerIdAssigner, SnowflakeProperties snowflakeProperties) {
                super(frameworkWorkerIdAssigner, snowflakeProperties);
            }

            @Override
            protected CachedUidGenerator uidGenerator() throws Exception {
                CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
                cachedUidGenerator.setWorkerIdAssigner(frameworkWorkerIdAssigner);
                cachedUidGenerator.setTimeBits(snowflakeProperties.getTimeBits());
                cachedUidGenerator.setWorkerBits(snowflakeProperties.getWorkerBits());
                cachedUidGenerator.setSeqBits(snowflakeProperties.getSeqBits());
                cachedUidGenerator.setEpochStr(EPOCH);
                cachedUidGenerator.setBoostPower(snowflakeProperties.getBoostPower());
                cachedUidGenerator.setScheduleInterval(60L);
                cachedUidGenerator.afterPropertiesSet();
                return cachedUidGenerator;
            }

        }

    }
}
