package com.lukgru.galgo.builder.fitness;

import com.lukgru.galgo.model.FitnessFunction;

import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class SimpleFitnessFunctionAccessor<T> implements FitnessFunctionAccessor<T> {
    private final Function<T, Integer> fitnessFunction;
    private Integer target = 0;

    public SimpleFitnessFunctionAccessor(Function<T, Integer> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public SimpleFitnessFunctionAccessor<T> targeting(Integer target) {
        this.target = target;
        return this;
    }

    @Override
    public FitnessFunction<T> getFitnessFunction() {
        return fitnessFunction != null ? new FitnessFunction<>(fitnessFunction, target) : null;
    }
}
