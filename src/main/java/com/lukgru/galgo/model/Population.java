package com.lukgru.galgo.model;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * Created by Lukasz on 01.12.2016.
 */
public class Population<T> {

    private final Collection<T> population;

    public Population(Collection<T> population) {
        this.population = population;
    }

    public Collection<T> getPopulation() {
        //TODO: return copy
        return population;
    }
}
