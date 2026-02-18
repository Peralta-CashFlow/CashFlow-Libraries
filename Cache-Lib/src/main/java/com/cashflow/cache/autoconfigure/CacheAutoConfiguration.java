package com.cashflow.cache.autoconfigure;

import com.cashflow.cache.config.RedisConfig;
import com.cashflow.cache.service.CacheService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@AutoConfiguration
@Import(RedisConfig.class)
public class CacheAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Map<String, Duration> cacheDurations() {
        return new HashMap<>();
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheService cacheService(org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate) {
        return new CacheService(redisTemplate);
    }
}
