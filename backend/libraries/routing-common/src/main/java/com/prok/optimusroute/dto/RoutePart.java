package com.prok.optimusroute.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class RoutePart {
    private Deque<Point> points;
    private List<Coordinate> route;
    private LocalDateTime startTime;
    private LocalDateTime endLimit;
    private LocalDateTime calcDateTime;
    private List<Point> unusedPoints;
    private Double distant;
    private Long time;
    private Long waitTime;

    public boolean addPoint(Point point, Path path) {
        if (calcDateTime.isAfter(point.getEndWorkPoint())) {
            return false;
        }

        if (calcDateTime.isBefore(point.getStartWorkPoint())) {
            point.addWaitTime(Duration.between(startTime, calcDateTime).toSeconds());
        }

        calcDateTime = calcDateTime.plusSeconds(path.getTime()).plusSeconds(point.getDelay());

        if (calcDateTime.isAfter(point.getEndWorkPoint()) && !point.isDelayAfterEnd()
                || calcDateTime.isAfter(endLimit)) {
            return false;
        }

        time = time + point.getDelay() + path.getTime();
        distant += path.getDistance();
        route.addAll(path.getCoordinates());
        waitTime += point.getWaitTime();
        unusedPoints.remove(point);
        points.add(point);

        return true;
    }

    public RoutePart setStartTime(LocalDateTime date) {
        startTime = date;
        calcDateTime = date;
        return this;
    }

    public boolean isValid() {
        return calcDateTime.isBefore(endLimit);
    }

    public boolean usedAllRequiredPoint() {
        return unusedPoints.stream().noneMatch(Point::isMandatory);
    }

    //<editor-fold desc="Getter">

    public Point getFirstPoint() {
        return points.getFirst();
    }

    public Point getLastPoint() {
        return points.getLast();
    }

    public Optional<Point> getUnusedPoint(Coordinate coordinate) {
        return unusedPoints.stream()
                .filter(point -> point.getCoordinate() == coordinate)
                .findFirst();
    }

    //</editor-fold>

    //<editor-fold desc="Creator">

    public static RoutePart create(Point startPoint) {
        RoutePart routePart = new RoutePart()
                .setPoints(new LinkedList<>())
                .setRoute(new ArrayList<>())
                .setUnusedPoints(new ArrayList<>());
        routePart.getPoints().push(startPoint);

        return routePart;
    }

    public static RoutePart create(RoutePart routePart) {
        return new RoutePart()
                .setPoints(routePart.getPoints())
                .setRoute(routePart.getRoute())
                .setStartTime(routePart.getStartTime())
                .setEndLimit(routePart.getEndLimit())
                .setCalcDateTime(routePart.getCalcDateTime())
                .setUnusedPoints(routePart.getUnusedPoints())
                .setDistant(routePart.getDistant())
                .setTime(routePart.getTime());
    }

    //</editor-fold>

}
