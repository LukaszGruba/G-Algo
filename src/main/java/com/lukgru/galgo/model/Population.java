package com.lukgru.galgo.model;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lukasz on 01.12.2016.
 */
public class Population<T> {

    private final Collection<Individual<T>> individuals;

    public Population(Collection<T> individuals) {
        if (CollectionUtils.isEmpty(individuals)) {
            throw new IllegalArgumentException("Population cannot be null nor empty.");
        }
        List<Individual<T>> collect = individuals.stream().map(Individual::new).collect(Collectors.toList());
        this.individuals = Collections.unmodifiableCollection(collect);
    }

    public Collection<Individual<T>> getIndividuals() {
        return individuals;
    }
}
