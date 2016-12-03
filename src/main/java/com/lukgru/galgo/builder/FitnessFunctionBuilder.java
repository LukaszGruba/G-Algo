package com.lukgru.galgo.builder;

import com.lukgru.galgo.model.CrossoverFunction;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class FitnessFunctionBuilder<T> {
    private final Collection<T> population;
    private final Function<T, Integer> fitnessFunction;
    private Integer target = 0;

    public FitnessFunctionBuilder(Collection<T> population, Function<T, Integer> fitnessFunction) {
        this.population = population;
        this.fitnessFunction = fitnessFunction;
    }

    public GeneticAlgorithmRunnerBuilder<T> withCrossover(CrossoverFunction<T> crossoverFunction) {
        return new GeneticAlgorithmRunnerBuilder<>(population, fitnessFunction, target, crossoverFunction);
    }

    public FitnessFunctionBuilder<T> targeting(Integer target) {
        this.target = target;
        return this;
    }
}
