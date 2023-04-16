package com.prok.optimusroute.feign;

import com.prok.optimusroute.DistanceRequest;
import com.prok.optimusroute.DistanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DistanceFeignClient", url = "${feign.url}")
public interface DistanceFeignClient {
    @PostMapping("/getDistance")
    DistanceResponse getDistanceMatrix(@RequestBody DistanceRequest distanceRequest);
}
