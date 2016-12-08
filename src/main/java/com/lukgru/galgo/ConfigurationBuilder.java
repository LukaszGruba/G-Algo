package com.lukgru.galgo;

import com.lukgru.galgo.builder.mutation.MutationFunction;
import com.lukgru.galgo.builder.population.PopulationAccessor;
import com.lukgru.galgo.model.CrossoverFunction;
import com.lukgru.galgo.runner.GeneticAlgorithmRunner;

import java.util.function.Function;

/**
 * Created by Lukasz on 07.12.2016.
 */
public class ConfigurationBuilder<T> {
    private PopulationAccessor<T> populationAccessor;
    private Function<T, Integer> fitnessFunction;
    private Integer fitnessFunctionTarger;
    private CrossoverFunction crossoverFunction;
    private MutationFunction<T> mutationFunction;
    private Double mutationProbability;

    public ConfigurationBuilder<T> withPopulationAccessor(PopulationAccessor<T> populationAccessor) {
        this.populationAccessor = populationAccessor;
        return this;
    }

    public ConfigurationBuilder<T> withFitnessFunction(Function<T, Integer> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        return this;
    }

    public ConfigurationBuilder<T> targeting(Integer fitnessFunctionTarger) {
        this.fitnessFunctionTarger = fitnessFunctionTarger;
        return this;
    }

    public ConfigurationBuilder<T> withCrossover(CrossoverFunction<T> crossoverFunction) {
        this.crossoverFunction = crossoverFunction;
        return this;
    }

    public ConfigurationBuilder<T> withMutationFunction(MutationFunction<T> mutationFunction) {
        this.mutationFunction = mutationFunction;
        return this;
    }

    public ConfigurationBuilder<T> withMutationProbability(Double mutationProbability) {
        this.mutationProbability = mutationProbability;
        return this;
    }

    public GeneticAlgorithmRunner<T> runner() {
        return null;
    }
}
