package com.lukgru.galgo;

import com.lukgru.galgo.builder.PopulationBuilder;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public final class GAlgo {

    private GAlgo() {}

    public static <T> PopulationBuilder<T> fromPopulation(Collection<T> population) {
        return new PopulationBuilder<>(population);
    }

    //TODO: enable generation of population based on some factory method
}
