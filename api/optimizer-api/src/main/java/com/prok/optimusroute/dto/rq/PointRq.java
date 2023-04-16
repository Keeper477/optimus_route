package com.prok.optimusroute.dto.rq;

import com.prok.optimusroute.dto.Coordinate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointRq {
    @Schema(description = "Координаты точки")
    private Coordinate coordinate;

    @Schema(description = "Обязательность посещения")
    private Boolean mandatory;

    @Schema(description = "Флаг возсожности задержки после окончания работы точки")
    private Boolean delayAfterEnd;

    @Schema(description = "Задержка на точке")
    private Long delay;

    @Schema(description = "Время начала работы точки")
    private LocalDateTime startWorkPoint;

    @Schema(description = "Время конца работы точки")
    private LocalDateTime endWorkPoint;
}
