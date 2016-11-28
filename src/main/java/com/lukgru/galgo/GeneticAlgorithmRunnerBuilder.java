package com.lukgru.galgo;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GeneticAlgorithmRunnerBuilder<T> {
    private final Collection<T> population;
    private final Function<T, Integer> fitnessFunction;
    private Integer fitnessFunctionTarget;
    private final CrossoverFunction<T> crossoverFunction;

    private Function<T, T> mutationFunction;
    private double mutationProbability;

    public GeneticAlgorithmRunnerBuilder(Collection<T> population,
                                         Function<T, Integer> fitnessFunction,
                                         CrossoverFunction<T> crossoverFunction,
                                         Integer fitnessFunctionTarget) {
        this.population = population;
        this.fitnessFunction = fitnessFunction;
        this.crossoverFunction = crossoverFunction;
        this.fitnessFunctionTarget = fitnessFunctionTarget;
    }

    public GeneticAlgorithmRunnerBuilder<T> withMutation(Function<T, T> mutationFunction, double probability) {
        this.mutationFunction = mutationFunction;
        this.mutationProbability = probability;
        return this;
    }

    public GeneticAlgorithmRunner<T> runner() {
        return new SimpleGeneticAlgorithmRunner<>(settings());
    }

    private RunnerSettings<T> settings() {
        RunnerSettings<T> settings = new RunnerSettings<>();
        settings.setPopulation(population);
        settings.setFitnessFunction(fitnessFunction);
        settings.setFitnessFunctionTarget(fitnessFunctionTarget);
        settings.setCrossoverFunction(crossoverFunction);
        settings.setMutationFunction(mutationFunction);
        settings.setMutationProbability(mutationProbability);
        return settings;
    }
}
