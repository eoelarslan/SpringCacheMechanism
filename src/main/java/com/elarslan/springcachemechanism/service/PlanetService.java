package com.elarslan.springcachemechanism.service;

import com.elarslan.springcachemechanism.config.CacheConfig;
import com.elarslan.springcachemechanism.dto.PlanetX;
import com.elarslan.springcachemechanism.planetenum.LocationEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Cache Implementation Links:
 * https://docs.spring.io/spring-framework/docs/3.1.x/spring-framework-reference/html/cache.html
 * https://www.baeldung.com/spring-cache-tutorial
 */

@Service
public class PlanetService {
    @Cacheable(value = "isPlanetReachable", cacheManager = CacheConfig.LONG_CACHE_TIMEOUT, key = "#planetX")
    public boolean isPlanetReachable(PlanetX planetX) throws InterruptedException {
        if (LocationEnum.OUTER_SPACE.distanceFromEarth().compareTo(planetX.getPlanetLocationEnum().distanceFromEarth()) <= 0) {
            //TODO: Database operations
            return false;
        }
        return true;
    }
}
