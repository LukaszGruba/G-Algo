package com.lukgru.galgo;

import com.lukgru.galgo.builder.PopulationBuilder;
import com.lukgru.galgo.builder.PopulationGenerator;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Lukasz on 28.11.2016.
 */
public final class GAlgo {

    private GAlgo() {}

    public static <T> PopulationBuilder<T> fromPopulation(Collection<T> population) {
        return new PopulationBuilder<>(population);
    }

    public static <T> PopulationGenerator<T> fromGeneratedPopulation(Supplier<T> factory) {
        return new PopulationGenerator<>(factory);
    }

}
