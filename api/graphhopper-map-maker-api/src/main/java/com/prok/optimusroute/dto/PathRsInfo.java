package com.prok.optimusroute.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PathRsInfo {
    @Schema(description = "Время движения по дороге")
    private Long time;
    @Schema(description = "Длинна дороги")
    private Double distance;
    @Schema(description = "Координаты движения по дороге")
    private List<Coordinate> coordinates;

    public static PathRsInfo create() {
        return new PathRsInfo();
    }
}
