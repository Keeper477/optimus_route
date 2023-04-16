package com.prok.optimusroute.util;

import com.prok.optimusroute.dto.Coordinate;
import com.prok.optimusroute.dto.Point;
import com.prok.optimusroute.dto.RoutePart;
import com.prok.optimusroute.dto.rq.PointRq;
import com.prok.optimusroute.dto.rs.PointRs;
import com.prok.optimusroute.dto.rs.RoutePartRs;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class RequestUtil {

    //<editor-fold desc="Point">

    public static Point toPoint(PointRq rq) {
        return Point.create()
                .setCoordinate(rq.getCoordinate())
                .setMandatory(rq.getMandatory())
                .setDelayAfterEnd(rq.getDelayAfterEnd())
                .setDelay(rq.getDelay())
                .setStartWorkPoint(rq.getStartWorkPoint())
                .setEndWorkPoint(rq.getEndWorkPoint());
    }

    public static List<Point> toListPoint(List<PointRq> pointRqList) {
        List<Point> points = new ArrayList<>();
        pointRqList.forEach(pointRq -> points.add(toPoint(pointRq)));

        return points;
    }

    public static List<Coordinate> toListCoordinate(List<PointRq> points) {
        List<Coordinate> coordinates = new ArrayList<>();
        points.forEach(point -> coordinates.add(point.getCoordinate()));

        return coordinates;
    }

    //</editor-fold>

    //<editor-fold desc="RoutePartRs">

    public static RoutePartRs toRoutePartRs(RoutePart routePart, String vehicle) {
        return RoutePartRs.create()
                .setStartPoint(toPointRs(routePart.getFirstPoint()))
                .setPoints(toListPointRs(routePart.getPoints()))
                .setEndPoint(toPointRs(routePart.getLastPoint()))
                .setRoute(routePart.getRoute())
                .setVehicle(vehicle)
                .setStartRoutePart(routePart.getStartTime())
                .setEndRoutePart(routePart.getCalcDateTime())
                .setUnusedPoints(toListPointRs(routePart.getUnusedPoints()))
                .setDistant(routePart.getDistant())
                .setTime(routePart.getTime())
                .setWaitTime(routePart.getWaitTime());
    }

    public static List<RoutePartRs> toListRoutePartRs(List<RoutePart> routeParts, String vehicle) {
        List<RoutePartRs> routePartRsList = new ArrayList<>();
        routeParts.forEach(routePart -> routePartRsList.add(toRoutePartRs(routePart, vehicle)));

        return routePartRsList;
    }

    //</editor-fold>

    //<editor-fold desc="PointRs">

    public static PointRs toPointRs(Point point) {
        return PointRs.create()
                .setCoordinate(point.getCoordinate())
                .setMandatory(point.isMandatory())
                .setDelayAfterEnd(point.isDelayAfterEnd())
                .setDelay(point.getDelay())
                .setStartWorkPoint(point.getStartWorkPoint())
                .setEndWorkPoint(point.getEndWorkPoint())
                .setWaitTime(point.getWaitTime());
    }

    public static List<PointRs> toListPointRs(Collection<Point> points) {
        List<PointRs> pointRsList = new ArrayList<>();
        points.forEach(point -> pointRsList.add(toPointRs(point)));

        return pointRsList;
    }

    //</editor-fold>

}
