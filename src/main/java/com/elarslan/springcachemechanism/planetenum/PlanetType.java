package com.elarslan.springcachemechanism.planetenum;

public enum PlanetType {
    TERRESTRIAL(0),
    GAS_GIANT(1),
    ICE_GIANT(2),
    DWARF(3);

    private final int planetTypeCode;

    PlanetType(int planetTypeCode) {
        this.planetTypeCode = planetTypeCode;
    }

    public int planetTypeCode() {
        return planetTypeCode;
    }
}
