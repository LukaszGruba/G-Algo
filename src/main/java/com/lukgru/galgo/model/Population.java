package com.lukgru.galgo.model;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Lukasz on 01.12.2016.
 */
public class Population<T> {

    private final Collection<T> population;

    public Population(Collection<T> population) {
        if (CollectionUtils.isEmpty(population)) {
            throw new IllegalArgumentException("Population cannot be null nor empty.");
        }
        this.population = Collections.unmodifiableCollection(population);
    }

    public Collection<T> getPopulation() {
        return population;
    }
}
