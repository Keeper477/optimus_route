package com.prok.optimusroute.service;

import com.prok.optimusroute.RouteRequest;
import com.prok.optimusroute.RouteResponse;
import com.prok.optimusroute.algorithm.Algorithm;
import com.prok.optimusroute.algorithm.OptimizationAlgorithm;
import com.prok.optimusroute.dto.DistanceMatrix;
import com.prok.optimusroute.dto.rs.RoutePartRs;
import com.prok.optimusroute.dto.rs.RouteRs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OptimizationServiceImpl implements OptimizationService {
    private final String algorithm;
    private final DistanceService distanceService;

    public OptimizationServiceImpl(@Value("${optimizer.algorithm}") String algorithm, DistanceService distanceService) {
        this.algorithm = algorithm;
        this.distanceService = distanceService;
    }

    public RouteResponse getOptimizedRoute(RouteRequest rq) {

        OptimizationAlgorithm optimizationAlgorithm = Algorithm.getRealization(algorithm);

        RouteRs routeRs = RouteRs.create()
                .setStartRoute(rq.getStartRoute());
        AtomicReference<LocalDateTime> startRoutePartTime = new AtomicReference<>(rq.getStartRoute());

        rq.getParts().forEach(routePartRq -> {
            DistanceMatrix distanceMatrix = distanceService.getDistanceMatrix(routePartRq.getStartPoint(),
                    routePartRq.getPoints(),
                    routePartRq.getEndPoint(),
                    routePartRq.getVehicle());

            RoutePartRs routePartRs = optimizationAlgorithm.getOptimizedRoutePart(distanceMatrix, routePartRq, startRoutePartTime.get());

            startRoutePartTime.set(routePartRs.getEndRoutePart());
            routeRs.addPart(routePartRs);

        });

        return RouteResponse.create()
                .addRouteRs(routeRs);
    }

}
