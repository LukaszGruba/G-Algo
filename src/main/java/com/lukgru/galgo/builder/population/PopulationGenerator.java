package com.lukgru.galgo.builder.population;

import com.lukgru.galgo.builder.fitness.FitnessFunctionAccessor;
import com.lukgru.galgo.builder.fitness.FitnessFunctionBuilder;
import com.lukgru.galgo.model.Population;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Lukasz on 05.12.2016.
 */
public class PopulationGenerator<T> implements PopulationAccessor<T>, FitnessFunctionBuilder<T> {

    public static final int DEFAULT_POPULATION_SIZE = 100;

    private final Supplier<T> factory;
    private int populationSize;

    public PopulationGenerator(Supplier<T> factory) {
        this.factory = factory;
    }

    public PopulationAccessor<T> withSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    @Override
    public Population<T> getPopulation() {
        //TODO: generate population
        return null;
    }

    @Override
    public FitnessFunctionAccessor<T> withFitnessFunction(Function<T, Integer> fitnessFunction) {
        //TODO: implement
        return null;
    }
}
