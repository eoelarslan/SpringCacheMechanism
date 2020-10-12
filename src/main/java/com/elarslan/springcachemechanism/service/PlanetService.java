package com.elarslan.springcachemechanism.service;

import com.elarslan.springcachemechanism.dto.PlanetX;
import com.elarslan.springcachemechanism.planetenum.Location;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class PlanetService {
    public boolean isPlanetReachable(PlanetX planetX) throws InterruptedException {
        if (Location.OUTER_SPACE.distanceFromEarth().compareTo(planetX.getPlanetLocation().distanceFromEarth()) <= 0 ) {
            Thread.sleep(Duration.ofSeconds(5).toMillis());
            return false;
        }
        return true;
    }
}
