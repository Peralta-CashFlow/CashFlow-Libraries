package com.cashflow.cache.annotations.clear;

import com.cashflow.cache.service.CacheService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.annotation.Annotation;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CacheClearAspectTest {

    @InjectMocks
    private CacheClearAspect cacheClearAspect;

    @Mock
    private CacheService cacheService;

    @Mock
    private JoinPoint joinPoint;

    @Mock
    private MethodSignature methodSignature;

    @Test
    void givenCacheClearWithEmptyPatterns_whenClearCacheAfterMethod_thenDoNotInvalidateCache() {

        CacheClear cacheClear = new CacheClear() {
            @Override
            public String value() {
                return "categories";
            }

            @Override
            public String[] patterns() {
                return new String[0];
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return CacheClear.class;
            }
        };

        cacheClearAspect.clearCacheAfterMethod(joinPoint, cacheClear);

        verify(cacheService, never()).invalidateCacheByPattern(anyString());
    }

    @Test
    void givenCacheClearWithPatterns_whenClearCacheAfterMethod_thenInvalidateCache() {

        CacheClear cacheClear = new CacheClear() {
            @Override
            public String value() {
                return "categories";
            }

            @Override
            public String[] patterns() {
                return new String[]{"#userId + '-*'"};
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return CacheClear.class;
            }
        };

        Object[] args = new Object[]{123L};
        String[] paramNames = new String[]{"userId"};

        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getParameterNames()).thenReturn(paramNames);
        when(joinPoint.getArgs()).thenReturn(args);

        cacheClearAspect.clearCacheAfterMethod(joinPoint, cacheClear);

        verify(joinPoint).getSignature();
        verify(methodSignature).getParameterNames();
        verify(joinPoint).getArgs();

        verify(cacheService).invalidateCacheByPattern("categories::123-*");
    }
}