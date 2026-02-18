package com.cashflow.cache.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * Service class for cache management, providing methods to manage cache entries.
 *
 * @author Vinicius Peralta
 * @since 1.0.0
 *
 * @see #invalidateCacheByPattern(String)
 */
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheService(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Invalidates cache entries matching the provided key pattern.
     */
    public void invalidateCacheByPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}