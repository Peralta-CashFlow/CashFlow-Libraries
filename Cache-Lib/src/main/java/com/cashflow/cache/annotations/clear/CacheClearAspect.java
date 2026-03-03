package com.cashflow.cache.annotations.clear;

import com.cashflow.cache.service.CacheService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;

@Aspect
public class CacheClearAspect {

    private final CacheService cacheService;
    private final ExpressionParser parser = new SpelExpressionParser();

    public CacheClearAspect(final CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @AfterReturning("@annotation(cacheClear)")
    public void clearCacheAfterMethod(JoinPoint joinPoint, CacheClear cacheClear) {
        if (cacheClear.patterns().length > 0) {

            EvaluationContext context =
                    new StandardEvaluationContext();

            defineContext(joinPoint, context);

            Arrays.stream(cacheClear.patterns())
                    .forEach(cacheClearPattern -> {
                        String pattern = cacheClear.value() +
                                "::" +
                                parser.parseExpression(cacheClearPattern).getValue(context, String.class);
                        cacheService.invalidateCacheByPattern(pattern);
                    });
        }
    }

    private static void defineContext(JoinPoint joinPoint, EvaluationContext context) {

        MethodSignature signature =
                (MethodSignature) joinPoint.getSignature();

        String[] paramNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
    }


}
