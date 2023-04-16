package com.prok.optimusroute;

import com.prok.optimusroute.dto.PathRs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class DistanceResponse {
    @Schema(description = "Дороги")
    private List<PathRs> paths;

    public DistanceResponse addPath(PathRs pathRs) {
        paths.add(pathRs);

        return this;
    }

    public static DistanceResponse create() {
        return new DistanceResponse().setPaths(new ArrayList<>());
    }
}
