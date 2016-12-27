package com.lukgru.galgo;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.mutation.Mutation;
import com.lukgru.galgo.mutation.MutationFunction;
import com.lukgru.galgo.population.PopulationAccessor;
import com.lukgru.galgo.runner.GeneticAlgorithmRunner;
import com.lukgru.galgo.runner.SimpleGeneticAlgorithmRunner;

import java.util.function.Function;

/**
 * Created by Lukasz on 07.12.2016.
 */
public class ConfigurationBuilder<T> {
    private PopulationAccessor<T> populationAccessor;
    private Function<T, Double> fitnessFunction;
    private Double fitnessFunctionTarget;
    private CrossoverFunction<T> crossoverFunction;
    private MutationFunction<T> mutationFunction;
    private Double mutationProbability;
    private Double epsilon;

    public ConfigurationBuilder<T> withPopulationAccessor(PopulationAccessor<T> populationAccessor) {
        this.populationAccessor = populationAccessor;
        return this;
    }

    public ConfigurationBuilder<T> withFitnessFunction(Function<T, Double> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        return this;
    }

    public ConfigurationBuilder<T> targeting(Double fitnessFunctionTarger) {
        this.fitnessFunctionTarget = fitnessFunctionTarger;
        return this;
    }

    public ConfigurationBuilder<T> withEpsilon(Double epsilon) {
        this.epsilon = epsilon;
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
        FitnessFunction<T> fitnessFunctionObject = new FitnessFunction<>(fitnessFunction, fitnessFunctionTarget);
        Mutation<T> mutation = null;
        if (mutationFunction != null) {
            mutation = new Mutation<>(mutationFunction, mutationProbability);
        }
        return new SimpleGeneticAlgorithmRunner<>(
                populationAccessor,
                fitnessFunctionObject,
                epsilon,
                crossoverFunction,
                mutation);
    }
}
