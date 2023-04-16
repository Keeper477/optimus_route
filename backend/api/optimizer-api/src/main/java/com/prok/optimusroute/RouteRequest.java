package com.prok.optimusroute;

import com.prok.optimusroute.dto.rq.RoutePartRq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RouteRequest {
    @Schema(description = "Части маршрута")
    private List<RoutePartRq> parts;

    @Schema(description = "Время начала всего маршрута")
    private LocalDateTime startRoute;

    @Schema(description = "Ограничение по времени всего маршрута")
    private LocalDateTime endRoute;
}
