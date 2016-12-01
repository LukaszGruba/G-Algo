package com.lukgru.galgo.model;

import java.util.function.Function;

/**
 * Created by Lukasz on 01.12.2016.
 */
public class FitnessFunction<T> {
    private final Function<T, Integer> fitnessFunction;
    private final Integer target;

    public FitnessFunction(Function<T, Integer> fitnessFunction, Integer target) {
        this.fitnessFunction = fitnessFunction;
        this.target = target;
    }
}
