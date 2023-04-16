package com.prok.optimusroute.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Coordinate {
    private Double latitude;
    private Double longitude;

    public static Coordinate create() {
        return new Coordinate();
    }
}
