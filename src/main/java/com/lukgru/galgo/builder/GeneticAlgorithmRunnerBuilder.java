package com.lukgru.galgo.builder;

import com.lukgru.galgo.builder.mutation.MutationFunction;
import com.lukgru.galgo.model.CrossoverFunction;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Lukasz on 28.11.2016.
 */
//TODO: remove PMD ignoring
@SuppressWarnings("unused")
public class GeneticAlgorithmRunnerBuilder<T> {

    private final Collection<T> population;
    private final Function<T, Integer> fitnessFunction;
    private final Integer fitnessFunctionTarget;
    private final CrossoverFunction<T> crossoverFunction;
    private MutationFunction<T> mutationFunction;
    private Double mutationProbability;

    public GeneticAlgorithmRunnerBuilder(Collection<T> population, Function<T, Integer> fitnessFunction,
                                         Integer fitnessFunctionTarget,CrossoverFunction<T> crossoverFunction) {
        this.population = population;
        this.fitnessFunction = fitnessFunction;
        this.fitnessFunctionTarget = fitnessFunctionTarget;
        this.crossoverFunction = crossoverFunction;
    }

    public GeneticAlgorithmRunnerBuilder<T> withMutationFunction(MutationFunction<T> mutationFunction) {
        this.mutationFunction = mutationFunction;
        return this;
    }

    public GeneticAlgorithmRunnerBuilder<T> withMutationProbability(Double mutationProbability) {
        this.mutationProbability = mutationProbability;
        return this;
    }

//    public GeneticAlgorithmRunner<T> runner() {
//        Population<T> initialPopulation = new Population<>(population);
//        FitnessFunction<T> fitness = new FitnessFunction<>(fitnessFunction, fitnessFunctionTarget);
//        Mutation<T> mutation = new Mutation<>(mutationFunction, mutationProbability);
//
//        return new SimpleGeneticAlgorithmRunner<>(initialPopulation, fitness, crossoverFunction, mutation);
//    }

}
