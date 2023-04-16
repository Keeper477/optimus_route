package com.prok.optimusroute.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Coordinate {
    @Schema(description = "Широта точки")
    private Double latitude;

    @Schema(description = "Долгота точки")
    private Double longitude;
}
