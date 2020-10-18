package com.elarslan.springcachemechanism.dto;

import com.elarslan.springcachemechanism.planetenum.LocationEnum;
import com.elarslan.springcachemechanism.planetenum.PlanetTypeEnum;
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
    private PlanetTypeEnum planetTypeEnum;
    private LocationEnum planetLocationEnum;
}
