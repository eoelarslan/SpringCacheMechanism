package com.elarslan.springcachemechanism.config;

import com.elarslan.springcachemechanism.planetenum.CacheDurationEnum;
import com.elarslan.springcachemechanism.planetenum.CacheSizeEnum;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * https://access.redhat.com/documentation/en-us/red_hat_data_grid/7.1/html/developer_guide/integration_with_the_spring_framework
 * https://www.baeldung.com/spring-multiple-cache-managers
 */
@Configuration
@EnableCaching
public class CacheConfig {

    public static final String SHORT_CACHE_TIMEOUT = "shortCacheTimeout";
    public static final String MEDIUM_CACHE_TIMEOUT = "mediumCacheTimeout";
    public static final String LONG_CACHE_TIMEOUT = "longCacheTimeout";

    @Autowired
    private ApplicationContext applicationContext;

    @Primary
    @Bean(SHORT_CACHE_TIMEOUT)
    public SpringEmbeddedCacheManager shortTimeoutCacheManager() {
        return new SpringEmbeddedCacheManager(
                new DefaultCacheManager(
                        getOldGlobalConfiguration(),
                        getCacheConfiguration(
                                CacheMode.LOCAL,
                                CacheSizeEnum.SMALL_CACHE_SIZE.cacheSize(),
                                CacheDurationEnum.SHORT_CACHE_TIMEOUT.cacheDuration())));
    }

    @Bean(MEDIUM_CACHE_TIMEOUT)
    public SpringEmbeddedCacheManager mediumTimeoutCacheManager() {
        return new SpringEmbeddedCacheManager(
                new DefaultCacheManager(
                        getOldGlobalConfiguration(),
                        getCacheConfiguration(
                                CacheMode.LOCAL,
                                CacheSizeEnum.MEDIUM_CACHE_SIZE.cacheSize(),
                                CacheDurationEnum.MEDIUM_CACHE_TIMEOUT.cacheDuration())));
    }

    @Bean(LONG_CACHE_TIMEOUT)
    public SpringEmbeddedCacheManager longTimeoutCacheManager() {
        return new SpringEmbeddedCacheManager(
                new DefaultCacheManager(
                        getOldGlobalConfiguration(),
                        getCacheConfiguration(
                                CacheMode.LOCAL,
                                CacheSizeEnum.LARGE_CACHE_SIZE.cacheSize(),
                                CacheDurationEnum.LONG_CACHE_TIMEOUT.cacheDuration())));
    }

    private org.infinispan.configuration.cache.Configuration getCacheConfiguration(CacheMode cacheMode, Long cacheSize,
                                                                                   Long cacheDuration) {
        return new ConfigurationBuilder()
                .clustering()
                .cacheMode(cacheMode)
                .memory().size(cacheSize)
                .expiration().lifespan(cacheDuration, TimeUnit.MINUTES)
                .build();
    }

    private GlobalConfiguration getOldGlobalConfiguration() {
        return new GlobalConfigurationBuilder()
                .nonClusteredDefault()
                .globalJmxStatistics()
                .allowDuplicateDomains(true)
                .build();
    }

    @PreDestroy
    public void onPreDestroy() {
        Map<String, SpringEmbeddedCacheManager> cacheManagerMap = this.applicationContext.getBeansOfType(SpringEmbeddedCacheManager.class);

        if (!cacheManagerMap.isEmpty()) {
            cacheManagerMap.forEach((key, value) -> {
                if (value != null) {
                    value.stop();
                }
            });
        }
    }
}
