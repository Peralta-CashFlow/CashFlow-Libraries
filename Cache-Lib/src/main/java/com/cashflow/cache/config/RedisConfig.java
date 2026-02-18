package com.cashflow.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * This class configures Redis caching for the CashFlow application, defining a RedisCacheManager and a RedisTemplate bean.
 * <p>
 * For the configuration of the RedisCacheManager, it sets a default cache configuration with JSON serialization for
 * values, a default TTL of 10 minutes, and a key prefix based on the application properties.
 * <p>
 * Needed application properties include:
 * <ul>
 *     <li>spring.cache.redis.key-prefix: The prefix to be used for the application cache keys in Redis;</li>
 * </ul>
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 *
 * @see #cacheManager(RedisConnectionFactory)
 * @see #redisTemplate(RedisConnectionFactory)
 *
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${cache.key-prefix}")
    private String keyPrefix;

    private final RedisSerializationContext.SerializationPair<Object> jsonPair =
            RedisSerializationContext.SerializationPair
                    .fromSerializer(RedisSerializer.json());

    private final Map<String, Duration> cacheDurations;

    public RedisConfig(final Map<String, Duration> cacheDurations) {
        this.cacheDurations = cacheDurations;
    }

    /**
     *
     * Configures the RedisCacheManager with a default cache configuration and specific configurations for certain cache names if provided.
     * The default cache configuration includes JSON serialization for values, a TTL of 10 minutes, and a key prefix.
     * <p>
     * You can provide specific TTL durations for certain cache names by defining a Map of String (cache name) and Duration
     * (cache TTL) bean with name "cacheDurations" on your app.
     *
     * @author Vinicius Peralta
     * @since 1.0.0
     *
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(jsonPair)
                        .entryTtl(Duration.ofMinutes(10))
                        .disableCachingNullValues()
                        .computePrefixWith(cacheName -> keyPrefix + cacheName + "::");

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        if (!CollectionUtils.isEmpty(cacheDurations)) {
            cacheDurations.forEach((cacheName, duration) ->
                cacheConfigurations.put(cacheName,
                        defaultConfig.entryTtl(duration))
            );
        }

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

    /**
     * Configures the RedisTemplate with String serialization for keys and JSON serialization for values.
     * This template will be used for interacting with Redis directly in the application.
     *
     * @author Vinicius Peralta
     * @since 1.0.0
     *
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());

        template.afterPropertiesSet();

        return template;
    }

}
