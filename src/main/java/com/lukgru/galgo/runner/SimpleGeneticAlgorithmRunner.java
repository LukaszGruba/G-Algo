package com.lukgru.galgo.runner;

import com.lukgru.galgo.model.CrossoverFunction;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Łukasz on 2016-11-28.
 */
public class SimpleGeneticAlgorithmRunner<T> implements GeneticAlgorithmRunner<T> {

    private final CrossoverFunction<T> crossoverFunction;
    private final Function<T, Integer> fitnessFunction;
    private final Integer fitnessFunctionTarget;
    private final Function<T, T> mutationFunction;
    private Collection<T> population;

    public SimpleGeneticAlgorithmRunner(RunnerSettings<T> settings) {
        population = settings.getPopulation();
        crossoverFunction = settings.getCrossoverFunction();
        fitnessFunction = settings.getFitnessFunction();
        fitnessFunctionTarget = settings.getFitnessFunctionTarget();
        mutationFunction = settings.getMutationFunction();
    }

    @Override
    public GenerationResult<T> generate() {
        int iteration = 0;
        while (!solutionFound(population, fitnessFunction, fitnessFunctionTarget)) {
            population = newGeneration(population, fitnessFunction, fitnessFunctionTarget, crossoverFunction);
            population = mutate(population, mutationFunction);
            iteration++;
        }

        return new GenerationResult<>(population, iteration);
    }
}
