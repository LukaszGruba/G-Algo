package com.lukgru.galgo.population;

import java.util.Collection;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class SimplePopulationAccessor<T> implements PopulationAccessor<T> {

    private final Collection<T> population;

    public SimplePopulationAccessor(Collection<T> population) {
        this.population = population;
    }

    @Override
    public Population<T> getPopulation() {
        return new Population<>(population);
    }
}
