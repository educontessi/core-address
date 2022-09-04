package io.github.educontessi.core.address.adapters.out.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author Eduardo Possamai Contessi
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder
                .withCacheConfiguration("core-address-country",
                        cacheConfiguration().entryTtl(Duration.ofDays(1)))
                .withCacheConfiguration("core-address-state",
                        cacheConfiguration().entryTtl(Duration.ofDays(1)))
                .withCacheConfiguration("core-address-city",
                        cacheConfiguration().entryTtl(Duration.ofDays(1)))
                .withCacheConfiguration("core-address-neighborhood",
                        cacheConfiguration().entryTtl(Duration.ofDays(1)))
                .withCacheConfiguration("core-address-street",
                        cacheConfiguration().entryTtl(Duration.ofDays(1)))
                .withCacheConfiguration("core-address-zipcode",
                        cacheConfiguration().entryTtl(Duration.ofDays(1)))
                .withCacheConfiguration("core-address",
                        cacheConfiguration().entryTtl(Duration.ofDays(1)))
                .withCacheConfiguration("core-address-default",
                        cacheConfiguration().entryTtl(Duration.ofDays(1)));
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                LOGGER.info("Failure getting from cache: {}, exception: {}", cache.getName(), exception);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                LOGGER.info("Failure putting into cache: {}, exception: {}", cache.getName(), exception);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                LOGGER.info("Failure evicting from cache: {}, exception: {}", cache.getName(), exception);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                LOGGER.info("Failure clearing cache: {}, exception: {}", cache.getName(), exception);
            }
        };
    }

}
