package com.prok.optimusroute;

import com.prok.optimusroute.dto.Coordinate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DistanceRequest {
    @Schema(description = "Начальная точка маршрута")
    private Coordinate startCoordinate;

    @Schema(description = "Все точки паршрута")
    private List<Coordinate> coordinates;

    @Schema(description = "Конечная точка маршрута")
    private Coordinate endCoordinate;

    @Schema(description = "Средство передвидения")
    private String vehicle;

    public static DistanceRequest create() {
        return new DistanceRequest();
    }

}
