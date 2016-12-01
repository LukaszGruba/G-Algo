package com.lukgru.galgo.builder;

import com.lukgru.galgo.model.Population;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class PopulationBuilder<T> {

    private Population<T> population;

    public PopulationBuilder(Collection<T> population) {
        if (CollectionUtils.isEmpty(population)) {
            throw new IllegalArgumentException("PopulationBuilder cannot be empty");
        }
        this.population = new Population<>(population);
    }

    public FitnessFunctionBuilder<T> withFitnessFunction(Function<T, Integer> fitnessFunction) {
        return new FitnessFunctionBuilder<>(population, fitnessFunction);
    }
}
