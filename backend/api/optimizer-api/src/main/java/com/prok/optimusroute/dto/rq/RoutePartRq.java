package com.prok.optimusroute.dto.rq;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoutePartRq {
    @Schema(description = "Начальная точка части маршрута")
    private PointRq startPoint;

    @Schema(description = "Все точки части маршрута")
    private List<PointRq> points;

    @Schema(description = "Конечная точка части маршрута")
    private PointRq endPoint;

    @Schema(description = "Средство передвижения")
    private String vehicle;

    @Schema(description = "Время начала части маршрута")
    private LocalDateTime startRoutePart;

    @Schema(description = "Ограничение по времени части маршрута")
    private LocalDateTime endRoutePart;
}
