package com.lukgru.galgo.builder.population;

import com.lukgru.galgo.model.Population;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Created by Lukasz on 05.12.2016.
 */
public class PopulationGenerator<T> implements PopulationAccessor<T> {

    private final Supplier<T> factory;
    private Integer populationSize;

    public PopulationGenerator(Supplier<T> factory) {
        if (factory == null) {
            throw new NullPointerException("Factory cannot be null!");
        }
        this.factory = factory;
    }

    public PopulationAccessor<T> withSize(int populationSize) {
        if (populationSize <= 0) {
            throw new IllegalArgumentException("Population size has to be greater than 0!");
        }
        this.populationSize = populationSize;
        return this;
    }

    @Override
    public Population<T> getPopulation() {
        if (populationSize == null) {
            throw new IllegalStateException("Population size has to be provided!");
        }
        return generatePopulation();
    }

    private Population<T> generatePopulation() {
        ArrayList<T> individuals = new ArrayList<>(populationSize);
        for (int i = 0; i < populationSize; i++) {
            individuals.add(factory.get());
        }
        return new Population<>(individuals);
    }
}
