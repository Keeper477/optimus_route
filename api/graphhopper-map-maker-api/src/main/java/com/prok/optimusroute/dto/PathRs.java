package com.prok.optimusroute.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PathRs {

    @Schema(description = "Координата начала дороги")
    private Coordinate from;

    @Schema(description = "Координата конца дороги")
    private Coordinate to;

    @Schema(description = "Информация о дороге")
    private PathRsInfo pathInfo;

    public static PathRs create() {
        return new PathRs();
    }
}
