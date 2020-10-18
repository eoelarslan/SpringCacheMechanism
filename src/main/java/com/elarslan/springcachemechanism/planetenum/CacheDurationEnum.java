package com.elarslan.springcachemechanism.planetenum;

import com.elarslan.springcachemechanism.PlanetConstant;

import java.time.Duration;

public enum CacheDurationEnum {
    LONG_CACHE_TIMEOUT(Duration.ofHours(6L).toMinutes(), PlanetConstant.MINUTE),
    MEDIUM_CACHE_TIMEOUT(Duration.ofHours(1L).toMinutes(), PlanetConstant.MINUTE),
    SHORT_CACHE_TIMEOUT(Duration.ofMinutes(10L).toMinutes(), PlanetConstant.MINUTE);


    private final Long cacheDuration;
    private final String cacheDurationType;

    CacheDurationEnum(Long cacheDuration, String cacheDurationType) {
        this.cacheDuration = cacheDuration;
        this.cacheDurationType = cacheDurationType;
    }

    public Long cacheDuration() {
        return cacheDuration;
    }

    public String cacheDurationType() {
        return cacheDurationType;
    }
}
