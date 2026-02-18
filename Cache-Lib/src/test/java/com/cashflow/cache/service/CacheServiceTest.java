package com.cashflow.cache.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CacheServiceTest {

    private final RedisTemplate<String, Object> redisTemplate = Mockito.mock(RedisTemplate.class);

    private final CacheService cacheService = new CacheService(redisTemplate);

    @Test
    void givenExistentKeys_whenInvalidateCacheByPattern_thenDeleteKeys() {
        String pattern = "test*";
        Set<String> keys = Set.of("TEST");
        when(redisTemplate.keys(pattern)).thenReturn(keys);
        cacheService.invalidateCacheByPattern(pattern);
        verify(redisTemplate, times(1)).delete(keys);
    }

    @Test
    void givenNonExistentKeys_whenInvalidateCacheByPattern_thenDoNotDeleteKeys() {
        String pattern = "test*";
        when(redisTemplate.keys(pattern)).thenReturn(Set.of());
        cacheService.invalidateCacheByPattern(pattern);
        verify(redisTemplate, never()).delete(anySet());
    }
  
}