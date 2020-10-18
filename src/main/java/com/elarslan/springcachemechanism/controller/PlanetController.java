package com.elarslan.springcachemechanism.controller;

import com.elarslan.springcachemechanism.dto.PlanetX;
import com.elarslan.springcachemechanism.dto.base.GenericResponseDto;
import com.elarslan.springcachemechanism.planetenum.LocationEnum;
import com.elarslan.springcachemechanism.planetenum.PlanetTypeEnum;
import com.elarslan.springcachemechanism.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8182/swagger-ui.html#/planet-controller
 */
@RestController
@RequestMapping(value = "/voyageOne")
public class PlanetController {

    @Autowired
    PlanetService planetService;

    @GetMapping("/getReachablePlanet")
    public ResponseEntity<?> getReachablePlanet(@RequestParam String planetName,
                                                @RequestParam PlanetTypeEnum planetTypeEnum,
                                                @RequestParam LocationEnum planetLocationEnum) throws InterruptedException {
        PlanetX planetX = PlanetX.builder()
                .name(planetName)
                .planetTypeEnum(planetTypeEnum)
                .planetLocationEnum(planetLocationEnum)
                .build();

        return ResponseEntity.ok().body(new GenericResponseDto<>(HttpStatus.OK,
                "Is planet reachable", planetService.isPlanetReachable(planetX)));
    }
}
