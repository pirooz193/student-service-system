package com.example.studentserviceapplication.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {
//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("facultiesCache");
//    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        // create caches with different TTL
        ConcurrentMapCache facultiesCache = new ConcurrentMapCache("faculties",
                CacheBuilder.newBuilder()
                        .maximumSize(1000)
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build().asMap(),
                false);
        ConcurrentMapCache teachersCache = new ConcurrentMapCache("teachers",
                CacheBuilder.newBuilder()
                        .maximumSize(1000)
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build().asMap(),
                false);

        cacheManager.setCaches(Arrays.asList(facultiesCache, teachersCache));
        return cacheManager;
    }

}
