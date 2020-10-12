package com.elarslan.springcachemechanism.controller;

import com.elarslan.springcachemechanism.dto.PlanetX;
import com.elarslan.springcachemechanism.dto.base.GenericResponseDto;
import com.elarslan.springcachemechanism.planetenum.Location;
import com.elarslan.springcachemechanism.planetenum.PlanetType;
import com.elarslan.springcachemechanism.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * http://localhost:8182/swagger-ui.html#/planet-controller
 */
@RestController
@RequestMapping(value = "/voyageOne")
public class PlanetController {

    @Autowired
    PlanetService planetService;

    @GetMapping("/searchMovie")
    public ResponseEntity<?> getMovieDetail(@RequestParam String planetName,
                                            @RequestParam PlanetType planetType,
                                            @RequestParam Location planetLocation) throws InterruptedException {
        PlanetX planetX = PlanetX.builder()
                .name(planetName)
                .planetType(planetType)
                .planetLocation(planetLocation)
                .build();

        return ResponseEntity.ok().body(new GenericResponseDto<>(HttpStatus.OK,
                "Is planet reachable", planetService.isPlanetReachable(planetX)));
    }
}
