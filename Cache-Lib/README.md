# Commons Library

This library provides a set of feature to handle CashFlow cache with [Redis](https://redis.io/).

## Installation

You should add the bellow dependency to your pom.xml file:

```xml
<dependency>
  <groupId>com.peralta.cashflow</groupId>
  <artifactId>cashflow-cache</artifactId>
  <version>{{VERSION}}</version>
</dependency>
```

Version: You should look for the latest version on [GitHub Packages](https://github.com/Peralta-CashFlow/CashFlow-Libraries/packages/2861817);

## Features

Bellow you should found all the features for this Package.

- [RedisConfig](#redisconfig);
- [CacheService](#cacheservice);

## RedisConfig

The __RedisConfig__ is a configuration class that will be used to configure the Redis connection and also define the 
RedisTemplate bean that will be used to interact with Redis.

To connect your application to Redis, you should add the bellow configuration to your properties file:

```properties
spring.data.redis.url=Redis connection URL
cache.key-prefix=Prefix for all cache keys for this application
```

```yaml
spring:
  data:
    redis:
      url: Redis connection URL
cache:
  key-prefix: Prefix for all cache keys for this application
```

Also, this configuration class define specific cacheDurations using a Map that should be defined on your application if
you want to use specific cache durations for different cache keys. The map should be defined with the bellow format:

```java
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@Bean
public Map<String, Long> cacheDurations() {
    Map<String, Long> cacheDurations = new HashMap<>();
    cacheDurations.put("cacheKey1", Duration.ofMinutes(15L));
    cacheDurations.put("cacheKey2", Duration.ofMinutes(30L));
    return cacheDurations;
}
```

## CacheService

The __CacheService__ provides a set of methods to interact with Redis cache. It uses the RedisTemplate bean defined in 
the RedisConfig class to manage the cache, bellow you can find the implemented methods:

### invalidateCacheByPattern(String pattern)

This method will invalidate the cache for the given key. It will remove the cache from Redis, example of using:

```java
import com.cashflow.cache.service.CacheService;

private final CacheService cacheService;

private void example() {
    cacheService.invalidateCacheByPattern("cacheKey1-*");   
}
```