package com.prok.optimusroute;

import com.prok.optimusroute.dto.rs.RouteRs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class RouteResponse {
    @Schema(description = "Подходящие маршруты")
    private List<RouteRs> routes;

    public RouteResponse addRouteRs(RouteRs routeRs) {
        routes.add(routeRs);
        return this;
    }

    public static RouteResponse create() {
        return new RouteResponse()
                .setRoutes(new ArrayList<>());
    }
}
