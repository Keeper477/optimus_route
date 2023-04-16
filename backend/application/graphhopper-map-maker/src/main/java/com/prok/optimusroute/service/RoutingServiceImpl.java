package com.prok.optimusroute.service;

import com.graphhopper.GHRequest;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.util.Parameters;
import com.prok.optimusroute.DistanceRequest;
import com.prok.optimusroute.DistanceResponse;
import com.prok.optimusroute.dto.Coordinate;
import com.prok.optimusroute.dto.PathRs;
import com.prok.optimusroute.dto.PathRsInfo;
import com.prok.optimusroute.util.GraphHopperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutingServiceImpl implements RoutingService {
    private final GraphHopper graphHopper;

    public DistanceResponse getDistance(DistanceRequest rq) {
        DistanceResponse rs = DistanceResponse.create();

        rq.getCoordinates().parallelStream().forEach(to -> rs.addPath(PathRs.create()
                .setFrom(rq.getStartCoordinate())
                .setTo(to)
                .setPathInfo(getRouteByTwoPoint(rq.getStartCoordinate(), to, rq.getVehicle()))));

        rq.getCoordinates().parallelStream().forEach(from -> rq.getCoordinates().forEach(to -> {
            if (from != to) {
                rs.addPath(PathRs.create()
                        .setFrom(from)
                        .setTo(to)
                        .setPathInfo(getRouteByTwoPoint(from, to, rq.getVehicle())));
            }
        }));

        rq.getCoordinates().parallelStream().forEach(from -> rs.addPath(PathRs.create()
                .setFrom(from)
                .setTo(rq.getEndCoordinate())
                .setPathInfo(getRouteByTwoPoint(from, rq.getEndCoordinate(), rq.getVehicle()))));

        rs.addPath(PathRs.create()
                .setFrom(rq.getStartCoordinate())
                .setTo(rq.getEndCoordinate())
                .setPathInfo(getRouteByTwoPoint(rq.getStartCoordinate(), rq.getEndCoordinate(), rq.getVehicle())));

        return rs;
    }

    private PathRsInfo getRouteByTwoPoint(Coordinate start, Coordinate end, String vehicle) {
        GHRequest req = new GHRequest(GraphHopperUtil.toGHPoint(start), GraphHopperUtil.toGHPoint(end))
                .setAlgorithm(Parameters.Algorithms.DIJKSTRA_BI)
                .setProfile(vehicle)
                .setPathDetails(List.of(Parameters.Details.AVERAGE_SPEED));

        ResponsePath route = graphHopper.route(req).getBest();

        return PathRsInfo.create()
                .setTime(route.getTime())
                .setDistance(route.getDistance())
                .setCoordinates(GraphHopperUtil.toListCoordinate(route.getPoints().iterator()));
    }

}
