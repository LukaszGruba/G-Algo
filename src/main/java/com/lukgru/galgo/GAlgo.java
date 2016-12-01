package com.lukgru.galgo;

import com.lukgru.galgo.builder.PopulationBuilder;

import java.util.Collection;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GAlgo {

    private GAlgo() {}

    public static <T> PopulationBuilder<T> fromPopulation(Collection<T> population) {
        return new PopulationBuilder<>(population);
    }
}
