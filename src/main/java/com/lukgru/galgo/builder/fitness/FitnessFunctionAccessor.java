package com.lukgru.galgo.builder.fitness;

import com.lukgru.galgo.model.FitnessFunction;

/**
 * Created by Lukasz on 06.12.2016.
 */
public interface FitnessFunctionAccessor<T> {

    FitnessFunction<T> getFitnessFunction();

}
