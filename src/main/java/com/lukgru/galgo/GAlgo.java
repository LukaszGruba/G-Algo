package com.lukgru.galgo;

import com.lukgru.galgo.builder.population.PopulationAccessor;
import com.lukgru.galgo.builder.population.PopulationGenerator;
import com.lukgru.galgo.builder.population.SimplePopulationAccessor;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by Lukasz on 28.11.2016.
 */
public final class GAlgo {

    private GAlgo() {}

    public static <T> ConfigurationBuilder<T> fromPopulation(Collection<T> population) {
        PopulationAccessor<T> simpleAccessor = new SimplePopulationAccessor<>(population);
        return new ConfigurationBuilder<T>().withPopulation(simpleAccessor);
    }

    public static <T> ConfigurationBuilder<T> fromGeneratedPopulation(Supplier<T> factory) {
        PopulationAccessor<T> populationGenerator = new PopulationGenerator<>(factory);
        return new ConfigurationBuilder<T>().withPopulation(populationGenerator);
    }

}
