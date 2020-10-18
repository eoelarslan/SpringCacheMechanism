package com.elarslan.springcachemechanism.controller;

import com.elarslan.springcachemechanism.config.CacheConfig;
import com.elarslan.springcachemechanism.dto.base.GenericResponseDto;
import com.elarslan.springcachemechanism.planetenum.CacheType;
import io.swagger.annotations.ApiOperation;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cache")
public class CacheController {

    @Autowired
    @Qualifier(CacheConfig.SHORT_CACHE_TIMEOUT)
    private SpringEmbeddedCacheManager shortCacheManger;

    @Autowired
    @Qualifier(CacheConfig.MEDIUM_CACHE_TIMEOUT)
    private CacheManager mediumCacheManger;

    @Autowired
    @Qualifier(CacheConfig.LONG_CACHE_TIMEOUT)
    private CacheManager longCacheManger;

    @GetMapping(value = "/evictCache")
    @ApiOperation(value = "This api created to clear specified cache", notes = "This api created to clear specified cache", response = ResponseEntity.class)
    public ResponseEntity<GenericResponseDto<Boolean>> evictCache(@RequestParam(value = "cacheTypes") List<CacheType> cacheTypes,
                                                               @RequestParam(value = "cacheName") String cacheName) {

        if (StringUtils.isEmpty(cacheName)) {
            //TODO: Logging will be implemented.
            throw new IllegalArgumentException("evictCache : cacheName cannot be null!");
        }
        if (CollectionUtils.isEmpty(cacheTypes)) {
            //TODO: Logging will be implemented.
            throw new IllegalArgumentException("evictCache : cacheTypes cannot be null!");
        }
        final List<CacheManager> cacheManagers = findCacheManager(cacheTypes);
        cacheManagers.forEach(cacheManager -> {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                //TODO: Logging will be implemented.
                cache.clear();
            }
        });
        return ResponseEntity.ok(new GenericResponseDto<>(HttpStatus.OK,"Data retrieved", true));
    }

    private List<CacheManager> findCacheManager(List<CacheType> cacheTypes) {
        List<CacheManager> cacheManagers = new ArrayList<>();
        if (cacheTypes.contains(CacheType.ALL)) {
            //TODO: Logging will be implemented.
            cacheManagers.add(shortCacheManger);
            cacheManagers.add(mediumCacheManger);
            cacheManagers.add(longCacheManger);
            return cacheManagers;
        }
        if (cacheTypes.contains(CacheType.SHORT_CACHE_TIMEOUT)) {
            //TODO: Logging will be implemented.
            cacheManagers.add(shortCacheManger);
        }
        if (cacheTypes.contains(CacheType.MEDIUM_CACHE_TIMEOUT)) {
            //TODO: Logging will be implemented.
            cacheManagers.add(mediumCacheManger);
        }
        if (cacheTypes.contains(CacheType.LONG_CACHE_TIMEOUT)) {
            //TODO: Logging will be implemented.
            cacheManagers.add(longCacheManger);
        }
        return cacheManagers;
    }
}
