package com.prok.optimusroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class DistanceMatrix {
    private Map<RelationKey, Path> distance;

    public List<RelationKey> getDestinations(Coordinate coordinate) {
        return distance.keySet().stream().filter(key -> key.getFrom() == coordinate).toList();
    }

    public void addDistance(Coordinate from, Coordinate to, Path response) {
        distance.put(new RelationKey(from, to), response);
    }

    public Path getPath(RelationKey key) {
        return distance.get(key);
    }


    @Data
    @AllArgsConstructor
    public static class RelationKey {
        private Coordinate from;
        private Coordinate to;
    }

    public static DistanceMatrix create() {
        return new DistanceMatrix()
                .setDistance(new HashMap<>());
    }
}
