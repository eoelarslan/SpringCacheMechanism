package com.elarslan.springcachemechanism.dto;

import com.elarslan.springcachemechanism.planetenum.Location;
import com.elarslan.springcachemechanism.planetenum.PlanetType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class PlanetX {
    private String name;
    private PlanetType planetType;
    private Location planetLocation;
}
