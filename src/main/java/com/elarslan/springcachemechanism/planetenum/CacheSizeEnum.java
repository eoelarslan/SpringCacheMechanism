package com.elarslan.springcachemechanism.planetenum;

public enum CacheSizeEnum {
    LARGE_CACHE_SIZE(1000L),
    MEDIUM_CACHE_SIZE(100L),
    SMALL_CACHE_SIZE(10L);


    private final Long cacheSize;

    CacheSizeEnum(Long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Long cacheSize() {
        return cacheSize;
    }
}
