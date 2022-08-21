package io.github.educontessi.core.address.adapters.in.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
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
public class RedisConfig {

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
        return (builder) -> builder
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

}
