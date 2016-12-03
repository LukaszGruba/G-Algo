package com.lukgru.galgo.builder;

import com.lukgru.galgo.model.CrossoverFunction;
import com.lukgru.galgo.model.FitnessFunction;
import com.lukgru.galgo.model.Mutation;
import com.lukgru.galgo.model.Population;
import com.lukgru.galgo.runner.GeneticAlgorithmRunner;
import com.lukgru.galgo.runner.SimpleGeneticAlgorithmRunner;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GeneticAlgorithmRunnerBuilder<T> {

    private final Collection<T> population;
    private final Function<T, Integer> fitnessFunction;
    private final Integer target;
    private final CrossoverFunction<T> crossoverFunction;
    private Function<T, T> mutationFunction;
    private Double mutationProbability;

    public GeneticAlgorithmRunnerBuilder(Collection<T> population, Function<T, Integer> fitnessFunction, Integer target, CrossoverFunction<T> crossoverFunction) {
        this.population = population;
        this.fitnessFunction = fitnessFunction;
        this.target = target;
        this.crossoverFunction = crossoverFunction;
    }

    public GeneticAlgorithmRunnerBuilder<T> withMutationFunction(Function<T, T> mutationFunction) {
        this.mutationFunction = mutationFunction;
        return this;
    }

    public GeneticAlgorithmRunnerBuilder<T> withMutationProbability(Double mutationProbability) {
        this.mutationProbability = mutationProbability;
        return this;
    }

    public GeneticAlgorithmRunner<T> runner() {
        Population<T> tPopulation = new Population<>(population);
        FitnessFunction<T> tFitnessFunction = new FitnessFunction<>(fitnessFunction, target);
        Mutation<T> tMutation = new Mutation<>(mutationFunction, mutationProbability);

        return new SimpleGeneticAlgorithmRunner<>(tPopulation, tFitnessFunction, crossoverFunction, tMutation);
    }

}
