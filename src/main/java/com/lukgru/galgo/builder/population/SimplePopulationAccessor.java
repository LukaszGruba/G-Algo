package com.lukgru.galgo.builder.population;

import com.lukgru.galgo.builder.fitness.FitnessFunctionBuilder;
import com.lukgru.galgo.builder.fitness.SimpleFitnessFunctionAccessor;
import com.lukgru.galgo.model.Population;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class SimplePopulationAccessor<T> implements PopulationAccessor<T>, FitnessFunctionBuilder<T> {

    private final Collection<T> population;

    public SimplePopulationAccessor(Collection<T> population) {
        this.population = population;
    }

    @Override
    public SimpleFitnessFunctionAccessor<T> withFitnessFunction(Function<T, Integer> fitnessFunction) {
        return new SimpleFitnessFunctionAccessor<>(fitnessFunction);
    }

    @Override
    public Population<T> getPopulation() {
        return new Population<>(population);
    }
}
