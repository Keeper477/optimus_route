package com.prok.optimusroute.algorithm;

import com.prok.optimusroute.exception.RoutingException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum Algorithm {
    BRUTE_FORCE("Brute Force", new BruteForce());

    private final String name;
    private final OptimizationAlgorithm realization;

    Algorithm(String name, OptimizationAlgorithm realization) {
        this.name = name;
        this.realization = realization;
    }

    public static OptimizationAlgorithm getRealization(String algorithm) {
        return Arrays.stream(values())
                .filter(optimizationAlgorithm -> Objects.equals(optimizationAlgorithm.name, algorithm))
                .findFirst()
                .orElseThrow(() -> new RoutingException("Не удалось выбрать алгоритм оптимизации"))
                .getRealization();
    }
}
