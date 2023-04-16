package com.prok.optimusroute.dto.rs;

import com.prok.optimusroute.dto.Coordinate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


@Data
@Accessors(chain = true)
public class PointRs {
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

    @Schema(description = "Время ожидания на точке")
    private Long waitTime;

    public static PointRs create() {
        return new PointRs();
    }
}
