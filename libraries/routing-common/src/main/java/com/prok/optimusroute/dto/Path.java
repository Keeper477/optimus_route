package com.prok.optimusroute.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class Path {
    private Long time;
    private Double distance;
    private List<Coordinate> coordinates;

    public static Path create() {
        return new Path()
                .setCoordinates(new ArrayList<>());
    }
}
