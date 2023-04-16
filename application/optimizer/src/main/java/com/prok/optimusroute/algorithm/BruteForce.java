package com.prok.optimusroute.algorithm;

import com.prok.optimusroute.dto.AnswerWrap;
import com.prok.optimusroute.dto.DistanceMatrix;
import com.prok.optimusroute.dto.Path;
import com.prok.optimusroute.dto.Point;
import com.prok.optimusroute.dto.RoutePart;
import com.prok.optimusroute.dto.rq.RoutePartRq;
import com.prok.optimusroute.dto.rs.RoutePartRs;
import com.prok.optimusroute.util.RequestUtil;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BruteForce implements OptimizationAlgorithm {

    public RoutePartRs getOptimizedRoutePart(DistanceMatrix distanceMatrix, RoutePartRq rq, LocalDateTime startTime) {

        List<RoutePart> routeParts = startRoute(distanceMatrix, rq, startTime);

        routeParts = routeParts.stream()
                .sorted(Comparator.comparing(RoutePart::getDistant)
                        .thenComparing(RoutePart::getWaitTime)
                        .thenComparing(RoutePart::getTime))
                .toList();

        return RequestUtil.toRoutePartRs(routeParts.get(0), rq.getVehicle());
    }


    private List<RoutePart> startRoute(DistanceMatrix distanceMatrix, RoutePartRq rq, LocalDateTime startTime) {
        Deque<RoutePart> routes = new LinkedList<>();
        AnswerWrap answers = AnswerWrap.create();

        routes.push(RoutePart.create(RequestUtil.toPoint(rq.getStartPoint()))
                .setStartTime(startTime)
                .setEndLimit(rq.getEndRoutePart())
                .setUnusedPoints(RequestUtil.toListPoint(rq.getPoints())));

        Point endPoint = RequestUtil.toPoint(rq.getEndPoint());

        while (!routes.isEmpty()) {
            RoutePart routePart = routes.pop();

            if (routePart.isValid() && answers.isActual(routePart.getCalcDateTime())) {
                distanceMatrix.getDestinations(routePart.getLastPoint().getCoordinate())
                        .forEach(track -> {
                            Optional<Point> to = routePart.getUnusedPoint(track.getTo());
                            if (to.isPresent()) {
                                RoutePart newRoute = RoutePart.create(routePart);
                                Path path = distanceMatrix.getPath(track);

                                if (newRoute.addPoint(to.get(), path)) {
                                    if (newRoute.getLastPoint() == endPoint && newRoute.usedAllRequiredPoint()) {
                                        answers.add(newRoute);
                                    } else {
                                        routes.push(newRoute);
                                    }
                                }
                            }
                        });
            }
        }

        return answers.getAnswers();
    }
}
