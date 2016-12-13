package com.lukgru.galgo.population;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Lukasz on 05.12.2016.
 */
public class PopulationGenerator<T> implements PopulationAccessor<T> {

    private final Supplier<T> factory;
    private Integer populationSize;

    public PopulationGenerator(Supplier<T> factory) {
        Objects.requireNonNull(factory, "Factory cannot be null!");
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
