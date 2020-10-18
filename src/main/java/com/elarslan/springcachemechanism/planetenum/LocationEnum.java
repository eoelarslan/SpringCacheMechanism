package com.elarslan.springcachemechanism.planetenum;

public enum LocationEnum {
    KUIPER_BELT(0, 15L),
    ANDROMEDA_GALAXY(1, 9451L),
    SAGITTARIUS(2, 8178L),
    PROXIMA_CEMTAURI(3, 1580L),
    OUTER_SPACE(4, 30L);

    private final int spaceAreaCode;
    private final Long distanceFromEarth;

    LocationEnum(int spaceAreaCode, Long distanceFromEarth) {
        this.spaceAreaCode = spaceAreaCode;
        this.distanceFromEarth = distanceFromEarth;
    }

    public int spaceAreaCode() {
        return spaceAreaCode;
    }

    public Long distanceFromEarth() {
        return distanceFromEarth;
    }
}
