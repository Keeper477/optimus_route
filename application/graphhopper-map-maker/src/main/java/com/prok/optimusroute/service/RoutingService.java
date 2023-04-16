package com.prok.optimusroute.service;

import com.prok.optimusroute.DistanceRequest;
import com.prok.optimusroute.DistanceResponse;

public interface RoutingService {
    DistanceResponse getDistance(DistanceRequest distanceRequest);
}
