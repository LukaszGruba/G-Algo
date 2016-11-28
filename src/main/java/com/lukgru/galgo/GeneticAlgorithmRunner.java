package com.lukgru.galgo;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GeneticAlgorithmRunner<T> {
    private final Collection<T> population;
    private final Function<T, Integer> fitnessFunction;
    private final CrossoverFunction<T> crossoverFunction;
    private Integer target;

    private Function<T, T> mutationFunction;
    private double probability;

    public GeneticAlgorithmRunner(Collection<T> population,
                                  Function<T, Integer> fitnessFunction,
                                  CrossoverFunction<T> crossoverFunction,
                                  Integer target) {
        this.population = population;
        this.fitnessFunction = fitnessFunction;
        this.crossoverFunction = crossoverFunction;
        this.target = target;
    }

    public GeneticAlgorithmRunner<T> withMutation(Function<T, T> mutationFunction, double probability) {
        this.mutationFunction = mutationFunction;
        this.probability = probability;
        return this;
    }

    public GenerationResult generate() {
        //TODO: implement algorithm
        return null;
    }
}
