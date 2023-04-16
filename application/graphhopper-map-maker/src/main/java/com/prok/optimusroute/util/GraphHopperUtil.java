package com.prok.optimusroute.util;

import com.graphhopper.util.shapes.GHPoint;
import com.graphhopper.util.shapes.GHPoint3D;
import com.prok.optimusroute.dto.Coordinate;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@UtilityClass
public class GraphHopperUtil {
    public static GHPoint toGHPoint(Coordinate coordinate) {
        return new GHPoint(coordinate.getLatitude(), coordinate.getLongitude());
    }

    public static List<Coordinate> toListCoordinate(Iterator<GHPoint3D> iterator) {
        List<Coordinate> coordinates = new ArrayList<>();
        while (iterator.hasNext()) {
            var point = iterator.next();
            coordinates.add(Coordinate.create()
                    .setLatitude(point.getLat())
                    .setLongitude(point.getLon()));
        }
        return coordinates;
    }
}
