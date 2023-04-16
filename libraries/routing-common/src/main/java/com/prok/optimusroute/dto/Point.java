package com.prok.optimusroute.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Point {
    private Coordinate coordinate;
    private boolean mandatory;
    private boolean delayAfterEnd;
    private Long delay;
    private LocalDateTime startWorkPoint;
    private LocalDateTime endWorkPoint;
    private Long waitTime;

    public void addWaitTime(Long waitTime) {
        this.waitTime += waitTime;
    }

    public static Point create() {
        return new Point();
    }
}
