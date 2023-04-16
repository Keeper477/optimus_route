package com.prok.optimusroute.algorithm;

import com.prok.optimusroute.dto.DistanceMatrix;
import com.prok.optimusroute.dto.rq.RoutePartRq;
import com.prok.optimusroute.dto.rs.RoutePartRs;

import java.time.LocalDateTime;

public interface OptimizationAlgorithm {
    RoutePartRs getOptimizedRoutePart(DistanceMatrix distanceMatrix, RoutePartRq rq, LocalDateTime startTime);
}
