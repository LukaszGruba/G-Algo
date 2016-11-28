package com.lukgru.galgo;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class FitnessFunction<T> {
    private final Collection<T> population;
    private final Function<T, Integer> fitnessFunction;
    private Integer target;

    public FitnessFunction(Collection<T> population, Function<T, Integer> fitnessFunction) {
        this.population = population;
        this.fitnessFunction = fitnessFunction;
    }

    public GeneticAlgorithmRunnerBuilder<T> withCrossover(CrossoverFunction<T> crossoverFunction) {
        return new GeneticAlgorithmRunnerBuilder<>(population, fitnessFunction, crossoverFunction, target);
    }

    public FitnessFunction<T> targeting(Integer target) {
        this.target = target;
        return this;
    }
}
