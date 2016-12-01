package com.lukgru.galgo.builder;

import com.lukgru.galgo.model.CrossoverFunction;
import com.lukgru.galgo.model.FitnessFunction;
import com.lukgru.galgo.model.Population;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class FitnessFunctionBuilder<T> {
    private final Population<T> population;
    private final Function<T, Integer> fitnessFunction;
    private Integer target;

    public FitnessFunctionBuilder(Population<T> population, Function<T, Integer> fitnessFunction) {
        this.population = population;
        this.fitnessFunction = fitnessFunction;
    }

    public GeneticAlgorithmRunnerBuilder<T> withCrossover(CrossoverFunction<T> crossoverFunction) {
        FitnessFunction fitnessFunction = new FitnessFunction(this.fitnessFunction, target);
        return new GeneticAlgorithmRunnerBuilder<>(population, fitnessFunction, crossoverFunction);
    }

    public FitnessFunctionBuilder<T> targeting(Integer target) {
        this.target = target;
        return this;
    }
}
