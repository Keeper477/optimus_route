package com.prok.optimusroute.controller;

import com.prok.optimusroute.RouteRequest;
import com.prok.optimusroute.RouteResponse;
import com.prok.optimusroute.service.OptimizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/optimization/v1")
public class OptimizationController {
    private final OptimizationService optimizationService;

    @PostMapping("/optimize")
    public ResponseEntity<RouteResponse> optimize(RouteRequest rq) {

        RouteResponse rs = optimizationService.getOptimizedRoute(rq);

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
