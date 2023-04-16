package com.prok.optimusroute.service;

import com.prok.optimusroute.dto.DistanceMatrix;
import com.prok.optimusroute.dto.rq.PointRq;

import java.util.List;

public interface DistanceService {
    DistanceMatrix getDistanceMatrix(PointRq start, List<PointRq> points, PointRq end, String vehicle);
}
