package com.prok.optimusroute.controller;

import com.prok.optimusroute.DistanceRequest;
import com.prok.optimusroute.DistanceResponse;
import com.prok.optimusroute.service.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routing/v1")
@RequiredArgsConstructor
public class RoutingController {
    private final RoutingService routingService;

    @PostMapping("/getDistance")
    public DistanceResponse getDistance(@RequestBody DistanceRequest distanceRequest) {
        return routingService.getDistance(distanceRequest);
    }
}
