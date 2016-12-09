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
        return new ConfigurationBuilder<T>().withPopulationAccessor(simpleAccessor);
    }

    public static <T> PopulationGeneratorBuilder<T> fromGeneratedPopulation(Supplier<T> factory) {
        return new PopulationGeneratorBuilder<>(factory);
    }

    public static class PopulationGeneratorBuilder<T> {
        private Supplier<T> factory;

        private PopulationGeneratorBuilder(Supplier<T> factory) {
            this.factory = factory;
        }

        public ConfigurationBuilder<T> withSize(int populationSize) {
            PopulationAccessor<T> populationGenerator = new PopulationGenerator<T>(factory).withSize(populationSize);
            return new ConfigurationBuilder<T>().withPopulationAccessor(populationGenerator);
        }
    }

}
