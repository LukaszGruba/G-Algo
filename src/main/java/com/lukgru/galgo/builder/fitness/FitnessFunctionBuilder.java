package com.lukgru.galgo.builder.fitness;

import java.util.function.Function;

/**
 * Created by Lukasz on 06.12.2016.
 */
public interface FitnessFunctionBuilder<T> {

    FitnessFunctionAccessor<T> withFitnessFunction(Function<T, Integer> fitnessFunction);

}
