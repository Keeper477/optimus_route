package com.prok.optimusroute.service;

import com.prok.optimusroute.RouteRequest;
import com.prok.optimusroute.RouteResponse;

public interface OptimizationService {
    RouteResponse getOptimizedRoute(RouteRequest rq);
}
