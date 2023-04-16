package com.prok.optimusroute.dto.rs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class RouteRs {
    @Schema(description = "Части маршрута")
    private List<RoutePartRs> parts;

    @Schema(description = "Время начала всего маршрута")
    private LocalDateTime startRoute;

    @Schema(description = "Временя окончания всего маршрута")
    private LocalDateTime endRoute;

    @Schema(description = "Неиспользуемые точки")
    private List<PointRs> unusedPoints;

    @Schema(description = "Общая длина маршрута")
    private Double distant;

    @Schema(description = "Общее время движения маршрута")
    private Long time;

    @Schema(description = "Общее время ожидания на маршруте")
    private Long waitTime;

    public RouteRs addPart(RoutePartRs routePartRs) {
        parts.add(routePartRs);
        endRoute = routePartRs.getEndRoutePart();
        unusedPoints.addAll(routePartRs.getUnusedPoints());
        distant += routePartRs.getDistant();
        time += routePartRs.getTime();
        waitTime += routePartRs.getTime();

        return this;
    }

    public static RouteRs create() {
        return new RouteRs()
                .setParts(new ArrayList<>());
    }
}
