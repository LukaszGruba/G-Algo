package com.lukgru.galgo;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class Population<T> {
    private Collection<T> population;

    public Population(Collection<T> population) {
        this.population = population;
    }

    public FitnessFunction<T> withFitnessFunction(Function<T, Integer> fitnessFunction) {
        return new FitnessFunction<>(population, fitnessFunction);
    }
}
