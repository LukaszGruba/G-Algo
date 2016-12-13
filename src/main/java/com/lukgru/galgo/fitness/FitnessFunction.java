package com.lukgru.galgo.fitness;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Lukasz on 01.12.2016.
 */
public class FitnessFunction<T> {
    private final Function<T, Integer> fitnessFunction;
    private final Integer target;

    public FitnessFunction(Function<T, Integer> fitnessFunction, Integer target) {
        Objects.requireNonNull(fitnessFunction, "Fitness function cannot be null.");
        Objects.requireNonNull(target, "Fitness target cannot be null.");
        this.fitnessFunction = fitnessFunction;
        this.target = target;
    }

    public Function<T, Integer> getFitnessFunction() {
        return fitnessFunction;
    }

    public Integer getTarget() {
        return target;
    }
}
