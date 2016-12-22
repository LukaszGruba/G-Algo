package com.lukgru.galgo.fitness;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Lukasz on 01.12.2016.
 */
public class FitnessFunction<T> {
    private final Function<T, Double> fitnessFunction;
    private final Double target;

    public FitnessFunction(Function<T, Double> fitnessFunction, Double target) {
        Objects.requireNonNull(fitnessFunction, "Fitness function cannot be null.");
        Objects.requireNonNull(target, "Fitness target cannot be null.");
        this.fitnessFunction = fitnessFunction;
        this.target = target;
    }

    public Function<T, Double> getFitnessFunction() {
        return fitnessFunction;
    }

    public Double getTarget() {
        return target;
    }
}
