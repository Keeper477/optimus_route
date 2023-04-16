package com.prok.optimusroute.service;

import com.prok.optimusroute.DistanceRequest;
import com.prok.optimusroute.DistanceResponse;
import com.prok.optimusroute.dto.DistanceMatrix;
import com.prok.optimusroute.dto.Path;
import com.prok.optimusroute.dto.rq.PointRq;
import com.prok.optimusroute.feign.DistanceFeignClient;
import com.prok.optimusroute.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceServiceImpl implements DistanceService {
    private final DistanceFeignClient distanceFeignClient;

    public DistanceMatrix getDistanceMatrix(PointRq start, List<PointRq> points, PointRq end, String vehicle) {
        DistanceResponse rs = distanceFeignClient.getDistanceMatrix(DistanceRequest.create()
                .setStartCoordinate(start.getCoordinate())
                .setCoordinates(RequestUtil.toListCoordinate(points))
                .setEndCoordinate(end.getCoordinate())
                .setVehicle(vehicle));

        DistanceMatrix distanceMatrix = DistanceMatrix.create();

        rs.getPaths().forEach(path -> distanceMatrix.addDistance(path.getFrom(), path.getTo(),
                Path.create()
                        .setDistance(path.getPathInfo().getDistance())
                        .setTime(path.getPathInfo().getTime())
                        .setCoordinates(path.getPathInfo().getCoordinates())));

        return distanceMatrix;
    }
}
