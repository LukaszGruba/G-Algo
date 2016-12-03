package com.lukgru.galgo.builder;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class PopulationBuilder<T> {

    private final Collection<T> population;

    public PopulationBuilder(Collection<T> population) {
        this.population = population;
    }

    public FitnessFunctionBuilder<T> withFitnessFunction(Function<T, Integer> fitnessFunction) {
        return new FitnessFunctionBuilder<>(population, fitnessFunction);
    }
}
