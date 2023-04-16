package com.prok.optimusroute.dto.rs;

import com.prok.optimusroute.dto.Coordinate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class RoutePartRs {
    @Schema(description = "Начальная точка части маршрута")
    private PointRs startPoint;

    @Schema(description = "Все ключевые точки")
    private List<PointRs> points;

    @Schema(description = "Конечная точка части маршрута")
    private PointRs endPoint;

    @Schema(description = "Точки для построения маршрута на карте")
    private List<Coordinate> route;

    @Schema(description = "Средство передвижения")
    private String vehicle;

    @Schema(description = "Время начала части маршрута")
    private LocalDateTime startRoutePart;

    @Schema(description = "Время конца части маршрута")
    private LocalDateTime endRoutePart;

    @Schema(description = "Неиспользуемые точки")
    private List<PointRs> unusedPoints;

    @Schema(description = "Длина части маршрута")
    private Double distant;

    @Schema(description = "Время движения части маршрута")
    private Long time;

    @Schema(description = "Время ожидания на части маршрута")
    private Long waitTime;

    public static RoutePartRs create() {
        return new RoutePartRs();
    }

}
